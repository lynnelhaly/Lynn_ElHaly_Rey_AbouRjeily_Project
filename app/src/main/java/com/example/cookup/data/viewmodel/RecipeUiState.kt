package com.example.cookup.data.viewmodel

import com.example.cookup.data.remote.model.RecipeDto

data class RecipeUiState(
    val recipes: List<RecipeDto> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
