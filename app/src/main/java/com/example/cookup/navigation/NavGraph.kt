package com.example.cookup.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cookup.data.viewmodel.AuthViewModel
import com.example.cookup.ui.screens.AboutUsScreen
import com.example.cookup.ui.screens.IngredientsScreen
import com.example.cookup.ui.screens.LoginScreen
import com.example.cookup.ui.screens.RecipesScreen
import com.example.cookup.ui.screens.RegisterScreen
import com.example.cookup.ui.screens.WelcomeScreen
import com.example.cookup.ui.screens.RecipeDetailScreen
import com.example.cookup.ui.screens.FavoritesScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavRoutes.WelcomeScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(NavRoutes.WelcomeScreen.route) {
                WelcomeScreen(navController = navController)
            }

            composable(NavRoutes.LoginScreen.route) {
                LoginScreen(navController, authViewModel)
            }

            composable(NavRoutes.RegisterScreen.route) {
                RegisterScreen(navController, authViewModel)
            }

            composable(NavRoutes.IngredientsScreen.route) {
                IngredientsScreen(navController)
            }

            composable(
                route = NavRoutes.RecipesScreen.route,
                arguments = listOf(
                    navArgument("ids") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val names = backStackEntry.arguments
                    ?.getString("ids")
                    ?.split(",")
                    ?.map { java.net.URLDecoder.decode(it, "UTF-8") }
                    ?.filter { it.isNotBlank() }
                    ?: emptyList()


                RecipesScreen(
                    navController = navController,
                    ingredientNames = names
                )
            }

            composable(NavRoutes.AboutUsScreen.route) {
                AboutUsScreen(navController)
            }

            composable(
                route = NavRoutes.RecipeDetailRoute.route,
                arguments = listOf(
                    navArgument("recipeId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val recipeId =
                    backStackEntry.arguments?.getInt("recipeId")
                        ?: return@composable

                RecipeDetailScreen(
                    navController = navController,
                    recipeId = recipeId
                )
            }
            composable(NavRoutes.FavoritesScreen.route) {
                FavoritesScreen(navController)
            }

        }
    }
}
