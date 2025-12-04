package com.example.cookup.data

import com.example.cookup.data.local.RecipeDao
import com.example.cookup.data.model.IngredientEntity
import com.example.cookup.data.model.RecipeEntity
import com.example.cookup.data.model.RecipeIngredientCrossRef
import com.example.cookup.data.model.RecipeWithMatchCount

class CookUpRepository(
    private val dao: RecipeDao
) {

    // -----------------------------------------
    // INGREDIENTS
    // -----------------------------------------
    suspend fun getAllIngredients(): List<IngredientEntity> =
        dao.getAllIngredients()

    suspend fun addIngredient(name: String): Long =
        dao.insertIngredient(IngredientEntity(name = name))


    // -----------------------------------------
    // RECIPES
    // -----------------------------------------
    suspend fun insertRecipe(recipe: RecipeEntity): Long =
        dao.insertRecipe(recipe)

    suspend fun getRecipeById(id: Int): RecipeEntity =
        dao.getRecipeById(id)


    // -----------------------------------------
    // RECIPE–INGREDIENT RELATIONS
    // -----------------------------------------
    suspend fun insertRecipeIngredients(refs: List<RecipeIngredientCrossRef>) =
        dao.insertRecipeIngredients(refs)


    // -----------------------------------------
    // FILTERING BY INGREDIENTS (MATCH COUNT)
    // -----------------------------------------
    suspend fun getRecipesMatchingIngredients(ids: List<Int>): List<RecipeWithMatchCount> =
        dao.getRecipesByIngredients(ids)


    // -----------------------------------------
    // FAVORITES
    // -----------------------------------------
    suspend fun getFavoriteRecipes(): List<RecipeEntity> =
        dao.getFavoriteRecipes()

    suspend fun toggleFavorite(recipeId: Int, isFavorite: Boolean) =
        dao.updateFavorite(recipeId, isFavorite)
}
