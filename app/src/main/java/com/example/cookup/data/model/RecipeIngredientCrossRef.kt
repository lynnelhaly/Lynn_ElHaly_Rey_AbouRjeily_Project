package com.example.cookup.data.model

import androidx.room.Entity

@Entity(
    tableName = "recipe_ingredients",
    primaryKeys = ["recipeId", "ingredientId"]
)
data class RecipeIngredientCrossRef(
    val recipeId: Int,
    val ingredientId: Int
)
