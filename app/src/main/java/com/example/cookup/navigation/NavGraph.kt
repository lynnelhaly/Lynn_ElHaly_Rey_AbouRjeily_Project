package com.example.cookup.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cookup.CookUpApp
import com.example.cookup.data.viewmodel.*
import com.example.cookup.ui.components.CookUpTopBar
import com.example.cookup.ui.screens.*

@Composable
fun AppNavGraph(navController: NavHostController) {

    val app = LocalContext.current.applicationContext as CookUpApp
    val repo = app.repository

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: NavRoutes.WELCOME

    Scaffold(
        topBar = {
            CookUpTopBar(
                navController = navController,
                currentRoute = currentRoute.substringBefore("/")
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = NavRoutes.WELCOME // Set Welcome screen as start destination
        ) {

            // WELCOME SCREEN
            composable(NavRoutes.WELCOME) {
                WelcomeScreen(navController = navController, modifier = Modifier.padding(innerPadding))
            }

            // INGREDIENT SCREEN
            composable(NavRoutes.INGREDIENTS) {
                val vm: IngredientViewModel = viewModel(
                    factory = IngredientViewModelFactory(repo)
                )
                IngredientScreen(
                    navController = navController,
                    viewModel = vm,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            // RECIPE LIST SCREEN
            composable("${NavRoutes.RECIPES}/{ids}") { backStackEntry ->
                val idsRaw = backStackEntry.arguments?.getString("ids") ?: ""
                val idsList = idsRaw.split(",").filter { it.isNotBlank() }.map { it.toInt() }

                val vm: RecipeListViewModel = viewModel(
                    factory = RecipeListViewModelFactory(repo, idsList)
                )

                RecipeListScreen(
                    navController = navController,
                    viewModel = vm,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            // RECIPE DETAIL SCREEN
            composable("${NavRoutes.RECIPE_DETAIL}/{recipeId}") { backStackEntry ->
                val recipeId = backStackEntry.arguments?.getString("recipeId")?.toInt() ?: 0

                val vm: RecipeDetailViewModel = viewModel(
                    factory = RecipeDetailViewModelFactory(repo, recipeId)
                )

                RecipeDetailScreen(
                    navController = navController,
                    viewModel = vm,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            // FAVORITES SCREEN
            composable(NavRoutes.FAVORITES) {
                val vm: FavoritesViewModel = viewModel(
                    factory = FavoritesViewModelFactory(repo)
                )

                FavoritesScreen(
                    navController = navController,
                    viewModel = vm,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}
