package com.example.cookup.data.model

import androidx.room.Embedded

data class RecipeWithMatchCount(
    @Embedded val recipe: RecipeEntity,
    val matchCount: Int
)
