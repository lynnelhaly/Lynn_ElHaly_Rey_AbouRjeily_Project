package com.example.cookup.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.NavController
import com.example.cookup.R
import com.example.cookup.data.viewmodel.RecipeListViewModel
import com.example.cookup.navigation.NavRoutes
import com.example.cookup.ui.components.BackHeader

@Composable
fun RecipeListScreen(
    navController: NavController,
    viewModel: RecipeListViewModel,
    modifier: Modifier = Modifier
) {
    val recipes by viewModel.recipes.collectAsState()

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        // 🍽 Background image
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

                // 🔙 Header with back arrow
                BackHeader(
                    title = "Matched Recipes",
                    onBack = { navController.popBackStack() }
                )

                when {
                    recipes.isEmpty() -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No recipes found 🍳",
                                color = Color(0xFF7A6A5A)
                            )
                        }
                    }

                    else -> {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(recipes) { recipe ->

                                RecipeCard(
                                    title = recipe.recipe.title,
                                    matches = recipe.matchCount,
                                    onClick = {
                                        navController.navigate(
                                            NavRoutes.RecipeDetailRoute.createRoute(
                                                recipe.recipe.id
                                            )
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
    title: String,
    matches: Int,
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
                text = title,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF222222)
            )

            Text(
                text = "$matches matched ingredient(s)",
                fontSize = 14.sp,
                color = Color(0xFF7A6A5A)
            )
        }
    }
}
