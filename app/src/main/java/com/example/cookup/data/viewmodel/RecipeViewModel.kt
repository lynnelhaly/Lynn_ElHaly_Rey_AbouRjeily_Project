package com.example.cookup.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookup.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val repository: RecipeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecipeUiState())
    val uiState: StateFlow<RecipeUiState> = _uiState

    init {
        fetchRecipes()
    }

    private fun fetchRecipes() {
        viewModelScope.launch {
            _uiState.value = RecipeUiState(isLoading = true)

            try {
                val recipes = repository.getRecipes()
                _uiState.value = RecipeUiState(
                    recipes = recipes,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = RecipeUiState(
                    isLoading = false,
                    errorMessage = e.message
                )
            }
        }
    }
}
