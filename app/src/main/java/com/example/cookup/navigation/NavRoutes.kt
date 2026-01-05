package com.example.cookup.navigation

sealed class NavRoutes(val route: String) {

    object LoginScreen : NavRoutes("login_screen")
    object RegisterScreen : NavRoutes("register_screen")

    object WelcomeScreen : NavRoutes("welcome_screen")
    object IngredientsScreen : NavRoutes("ingredients_screen")
    object FavoritesScreen : NavRoutes("favorites_screen")
    object AboutUsScreen : NavRoutes("about_us_screen")

    object RecipesScreen : NavRoutes("recipes_screen/{ids}") {
        fun createRoute(ids: String) = "recipes_screen/$ids"
    }

    object RecipeDetailRoute : NavRoutes("recipe_detail/{recipeId}") {
        fun createRoute(recipeId: Int) = "recipe_detail/$recipeId"
    }
}
