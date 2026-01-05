package com.example.cookup.data.viewmodel

data class FavoritesUiState(
    val favoriteRecipeIds: List<Int> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
