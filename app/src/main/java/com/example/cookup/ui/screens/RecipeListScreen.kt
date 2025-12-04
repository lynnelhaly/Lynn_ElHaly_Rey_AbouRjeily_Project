package com.example.cookup.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cookup.data.viewmodel.RecipeListViewModel
import com.example.cookup.navigation.NavRoutes

@Composable
fun RecipeListScreen(
    navController: NavController,
    viewModel: RecipeListViewModel,
    modifier: Modifier = Modifier
) {
    val recipes by viewModel.recipes.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))  // minimal neutral background
            .padding(16.dp)
    ) {

        Text(
            text = "Matched Recipes",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF222222)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(recipes) { recipe ->
                RecipeItemMinimal(
                    title = recipe.recipe.title,
                    matches = recipe.matchCount,
                    onClick = {
                        navController.navigate(
                            NavRoutes.RECIPE_DETAIL + "/${recipe.recipe.id}"
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun RecipeItemMinimal(
    title: String,
    matches: Int,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp), // small depth
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFD9D9D9), RoundedCornerShape(12.dp))
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                color = Color(0xFF222222),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "$matches matched ingredient(s)",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
