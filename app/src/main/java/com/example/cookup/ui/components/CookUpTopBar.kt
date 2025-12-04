package com.example.cookup.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.cookup.navigation.NavRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CookUpTopBar(
    navController: NavController,
    currentRoute: String
) {
    // Hide the back button on the Welcome screen
    val showBack = currentRoute != NavRoutes.WELCOME

    // Show the Favorites button only if we're not on the Favorites screen
    val showFavorites = currentRoute != NavRoutes.FAVORITES

    TopAppBar(
        title = {
            Text(
                when (currentRoute) {
                    NavRoutes.INGREDIENTS -> "CookUp – Ingredients"
                    NavRoutes.RECIPES -> "Matched Recipes"
                    NavRoutes.RECIPE_DETAIL -> "Recipe Details"
                    NavRoutes.FAVORITES -> "Favorites"
                    else -> ""
                }
            )
        },
        navigationIcon = {
            // Show the back arrow if the current screen is not the Welcome screen
            if (showBack) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {
            // Show the favorites button if we are not on the Favorites screen
            if (showFavorites) {
                IconButton(onClick = {
                    navController.navigate(NavRoutes.FAVORITES)
                }) {
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
