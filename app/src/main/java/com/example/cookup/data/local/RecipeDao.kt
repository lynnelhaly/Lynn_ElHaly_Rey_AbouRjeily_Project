package com.example.cookup.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cookup.data.model.IngredientEntity
import com.example.cookup.data.model.RecipeEntity
import com.example.cookup.data.model.RecipeIngredientCrossRef
import com.example.cookup.data.model.RecipeWithMatchCount

@Dao
interface RecipeDao {

    // -----------------------------------------
    // INGREDIENTS
    // -----------------------------------------
    @Query("SELECT * FROM ingredients ORDER BY name")
    suspend fun getAllIngredients(): List<IngredientEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIngredient(ingredient: IngredientEntity): Long


    // -----------------------------------------
    // RECIPES
    // -----------------------------------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity): Long

    @Query("SELECT * FROM recipes WHERE id = :recipeId")
    suspend fun getRecipeById(recipeId: Int): RecipeEntity


    // -----------------------------------------
    // RECIPE–INGREDIENT CONNECTIONS
    // -----------------------------------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeIngredients(list: List<RecipeIngredientCrossRef>)


    // -----------------------------------------
    // RECIPE FILTERING BY INGREDIENT MATCH
    // -----------------------------------------
    @Query("""
        SELECT r.*, COUNT(ri.ingredientId) AS matchCount
        FROM recipes r
        INNER JOIN recipe_ingredients ri
            ON r.id = ri.recipeId
        WHERE ri.ingredientId IN (:ingredientIds)
        GROUP BY r.id
        ORDER BY matchCount DESC
    """)
    suspend fun getRecipesByIngredients(
        ingredientIds: List<Int>
    ): List<RecipeWithMatchCount>


    // -----------------------------------------
    // FAVORITES
    // -----------------------------------------
    @Query("SELECT * FROM recipes WHERE isFavorite = 1")
    suspend fun getFavoriteRecipes(): List<RecipeEntity>

    @Query("UPDATE recipes SET isFavorite = :isFav WHERE id = :recipeId")
    suspend fun updateFavorite(recipeId: Int, isFav: Boolean)
}
