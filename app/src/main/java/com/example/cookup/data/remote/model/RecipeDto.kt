package com.example.cookup.data.remote.model
data class RecipeDto(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val cookingTimeMinutes: Int = 0,
    val servings: Int = 0,
    val ingredients: List<String> = emptyList(),
    val steps: List<String> = emptyList(),
    val imageUrl: String = ""
)
