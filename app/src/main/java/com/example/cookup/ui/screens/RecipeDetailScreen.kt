package com.example.cookup.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
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
import coil.compose.AsyncImage
import com.example.cookup.CookUpApp
import com.example.cookup.R
import com.example.cookup.data.remote.model.RecipeDto
import com.example.cookup.data.viewmodel.FavoritesViewModel
import com.example.cookup.data.viewmodel.FavoritesViewModelFactory
import com.example.cookup.data.viewmodel.RecipeViewModel
import com.example.cookup.data.viewmodel.RecipeViewModelFactory
import com.example.cookup.ui.components.BackHeader

@Composable
fun RecipeDetailScreen(
    navController: NavHostController,
    recipeId: Int
) {
    val app = navController.context.applicationContext as CookUpApp

    val recipeViewModel: RecipeViewModel = viewModel(
        factory = RecipeViewModelFactory(app.recipeRepository)
    )

    val favoritesViewModel: FavoritesViewModel = viewModel(
        factory = FavoritesViewModelFactory(app.favoritesRepository)
    )

    val recipeState by recipeViewModel.uiState.collectAsState()
    val favoritesState by favoritesViewModel.uiState.collectAsState()

    val recipe: RecipeDto? =
        recipeState.recipes.find { it.id == recipeId }

    val isFavorite = favoritesState.favoriteRecipeIds.contains(recipeId)

    when {
        recipeState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        recipeState.errorMessage != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = recipeState.errorMessage ?: "Error loading recipe",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        recipe == null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Recipe not found")
            }
        }

        else -> {

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
                            .verticalScroll(rememberScrollState())
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(18.dp)
                    ) {

                        // 🔙 Header
                        BackHeader(
                            title = recipe.title,
                            onBack = { navController.popBackStack() },
                            trailing = {
                                IconButton(
                                    onClick = { favoritesViewModel.toggleFavorite(recipeId) }
                                ) {
                                    Icon(
                                        imageVector = if (isFavorite)
                                            Icons.Filled.Favorite
                                        else
                                            Icons.Outlined.FavoriteBorder,
                                        contentDescription = "Favorite",
                                        tint = Color(0xFFE07A3F)
                                    )
                                }
                            }
                        )


                        // 🖼 Image
                        AsyncImage(
                            model = if (recipe.imageUrl.isNotBlank())
                                recipe.imageUrl
                            else
                                "https://via.placeholder.com/800x400?text=No+Image",
                            contentDescription = recipe.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentScale = ContentScale.Crop
                        )

                        // 📝 Description
                        Text(
                            text = recipe.description,
                            fontSize = 15.sp,
                            color = Color(0xFF7A6A5A)
                        )

                        // ⏱ Info
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("⏱ ${recipe.cookingTimeMinutes} min")
                            Text("🍽 ${recipe.servings} servings")
                        }

                        // 🥕 Ingredients
                        SectionCard(title = "Ingredients") {
                            recipe.ingredients.forEach {
                                Text("• $it")
                            }
                        }

                        // 👩‍🍳 Steps
                        SectionCard(title = "Steps") {
                            recipe.steps.forEachIndexed { index, step ->
                                Text("${index + 1}. $step")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionCard(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFBF7)),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF6B4E3D)
            )
            content()
        }
    }
}
