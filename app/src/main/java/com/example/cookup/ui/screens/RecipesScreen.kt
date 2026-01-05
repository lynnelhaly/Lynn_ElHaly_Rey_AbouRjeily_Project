package com.example.cookup.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cookup.CookUpApp
import com.example.cookup.R
import com.example.cookup.data.remote.model.RecipeDto
import com.example.cookup.data.viewmodel.RecipeViewModel
import com.example.cookup.data.viewmodel.RecipeViewModelFactory
import com.example.cookup.navigation.NavRoutes
import com.example.cookup.ui.components.BackHeader


@Composable
fun RecipesScreen(
    navController: NavHostController,
    ingredientNames: List<String>
) {
    val app = navController.context.applicationContext as CookUpApp

    val recipeViewModel: RecipeViewModel = viewModel(
        factory = RecipeViewModelFactory(app.recipeRepository)
    )

    val state by recipeViewModel.uiState.collectAsState()

    val filteredRecipes = state.recipes.filter { recipe ->
        ingredientNames.any { selected ->
            val normalizedSelected = normalizeIngredient(selected)
            recipe.ingredients.any { ingredient ->
                normalizeIngredient(ingredient).contains(normalizedSelected)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // 🍽 Background
        Image(
            painter = androidx.compose.ui.res.painterResource(id = R.drawable.welcome_bg),
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
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                // 🔙 Header
                BackHeader(
                    title = "Matched Recipes",
                    onBack = { navController.popBackStack() }
                )


                when {
                    state.isLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    state.errorMessage != null -> {
                        Text(
                            text = state.errorMessage ?: "Error loading recipes",
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    filteredRecipes.isEmpty() -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No recipes found with the selected ingredients 🍳",
                                color = Color(0xFF7A6A5A)
                            )
                        }
                    }

                    else -> {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(filteredRecipes) { recipe ->
                                RecipeCard(
                                    recipe = recipe,
                                    onClick = {
                                        navController.navigate(
                                            NavRoutes.RecipeDetailRoute.createRoute(recipe.id)
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RecipeCard(
    recipe: RecipeDto,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFBF7)),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            Text(
                text = recipe.title,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF222222)
            )

            Text(
                text = recipe.description,
                fontSize = 14.sp,
                color = Color(0xFF7A6A5A)
            )

            Text(
                text = "Main ingredients: ${recipe.ingredients.joinToString(", ")}",
                fontSize = 13.sp,
                color = Color(0xFF7A6A5A)
            )
        }
    }
}

private fun normalizeIngredient(value: String): String {
    return value
        .lowercase()
        .replace(
            Regex("""\d+/?\d*\s*(cup|cups|tbsp|tablespoon|tablespoons|tsp|teaspoon|teaspoons)?"""),
            ""
        )
        .replace(Regex("""\b(tomatoes)\b"""), "tomato")
        .replace(Regex("""\b(eggs)\b"""), "egg")
        .replace(Regex("""\b(potatoes)\b"""), "potato")
        .replace(Regex("""\s+"""), " ")
        .trim()
}
