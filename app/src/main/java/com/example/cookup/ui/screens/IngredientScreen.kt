package com.example.cookup.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cookup.CookUpApp
import com.example.cookup.R
import com.example.cookup.data.viewmodel.IngredientViewModel
import com.example.cookup.data.viewmodel.IngredientViewModelFactory
import com.example.cookup.navigation.NavRoutes
import com.example.cookup.ui.components.BackHeader

@Composable
fun IngredientsScreen(
    navController: NavHostController
) {
    val app = navController.context.applicationContext as CookUpApp

    val ingredientViewModel: IngredientViewModel = viewModel(
        factory = IngredientViewModelFactory(app.ingredientRepository)
    )

    val state by ingredientViewModel.uiState.collectAsState()

    val selectedIngredients = remember { mutableStateListOf<String>() }
    val searchQuery = remember { mutableStateOf("") }
    val selectedCategory = remember { mutableStateOf("All") }

    val categories = remember(state.ingredients) {
        listOf("All") + state.ingredients.map { it.category }.distinct().sorted()
    }

    val filteredIngredients = state.ingredients.filter {
        it.name.contains(searchQuery.value, ignoreCase = true) &&
                (selectedCategory.value == "All" || it.category == selectedCategory.value)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // 🍽 Background
        Image(
            painter = painterResource(id = R.drawable.welcome_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Soft overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.65f))
        )

        // 🤍 Main Card
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .shadow(18.dp, RoundedCornerShape(28.dp)),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                // 🔙 Header + ❤️ Favorites
                BackHeader(
                    title = "Select Ingredients",
                    onBack = { navController.popBackStack() },
                    trailing = {
                        Text(
                            text = "❤️",
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .clickable {
                                    navController.navigate(
                                        NavRoutes.FavoritesScreen.route
                                    )
                                }
                        )
                    }
                )

                // 🔍 Search
                OutlinedTextField(
                    value = searchQuery.value,
                    onValueChange = { searchQuery.value = it },
                    label = { Text("Search ingredient") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                // 🏷 Categories
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(vertical = 4.dp)
                ) {
                    items(categories) { category ->
                        FilterChip(
                            selected = selectedCategory.value == category,
                            onClick = { selectedCategory.value = category },
                            label = { Text(category) }
                        )
                    }
                }

                // 🥕 Ingredients list
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(filteredIngredients) { ingredient ->
                        val isSelected =
                            selectedIngredients.contains(ingredient.name)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (isSelected)
                                        Color(0xFFE07A3F).copy(alpha = 0.12f)
                                    else
                                        Color.Transparent
                                )
                                .clickable {
                                    if (isSelected)
                                        selectedIngredients.remove(ingredient.name)
                                    else
                                        selectedIngredients.add(ingredient.name)
                                }
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = ingredient.name,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = ingredient.category,
                                    fontSize = 13.sp,
                                    color = Color(0xFF7A6A5A)
                                )
                            }

                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Selected",
                                    tint = Color(0xFFE07A3F)
                                )
                            }
                        }
                    }
                }

                // 🔥 Find Recipes
                Button(
                    onClick = {
                        val encodedNames = selectedIngredients
                            .joinToString(",") {
                                java.net.URLEncoder.encode(it, "UTF-8")
                            }

                        navController.navigate(
                            NavRoutes.RecipesScreen.createRoute(encodedNames)
                        )
                    },
                    enabled = selectedIngredients.isNotEmpty(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE07A3F),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Find Recipes",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
