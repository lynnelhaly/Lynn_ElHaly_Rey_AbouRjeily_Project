package com.example.cookup.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val cookingTimeMinutes: Int,
    val instructions: String,
    val category: String,
    val dietaryTags: String?,
    val isFavorite: Boolean = false
)
