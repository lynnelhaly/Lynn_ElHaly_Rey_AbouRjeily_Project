package com.example.cookup.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.cookup.navigation.NavRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CookUpTopBar(
    navController: NavController,
    currentRoute: String?
) {
    if (currentRoute == NavRoutes.LoginScreen.route) {
        return
    }

    val showBackButton =
        currentRoute != NavRoutes.WelcomeScreen.route &&
                currentRoute != NavRoutes.LoginScreen.route

    val showFavoritesButton =
        currentRoute != NavRoutes.FavoritesScreen.route &&
                currentRoute != NavRoutes.RecipeDetailRoute.route

    val title = when (currentRoute) {
        NavRoutes.IngredientsScreen.route -> "CookUp – Ingredients"
        NavRoutes.RecipesScreen.route -> "Matched Recipes"
        NavRoutes.RecipeDetailRoute.route -> "Recipe Details"
        NavRoutes.FavoritesScreen.route -> "Favorites"
        else -> "CookUp"
    }

    TopAppBar(
        title = { Text(title) },

        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },

        actions = {
            if (showFavoritesButton) {
                IconButton(
                    onClick = {
                        navController.navigate(NavRoutes.FavoritesScreen.route) {
                            launchSingleTop = true
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorites",
                        tint = Color.Red
                    )
                }
            }
        }
    )
}
