package com.example.cookup.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookup.data.repository.FavoritesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: FavoritesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState

    init {
        loadFavorites()
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _uiState.value = FavoritesUiState(isLoading = true)
            try {
                val favorites = repository.getFavoriteRecipeIds()
                _uiState.value = FavoritesUiState(
                    favoriteRecipeIds = favorites,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = FavoritesUiState(
                    isLoading = false,
                    errorMessage = e.message
                )
            }
        }
    }

    fun toggleFavorite(recipeId: Int) {
        viewModelScope.launch {
            if (repository.isFavorite(recipeId)) {
                repository.removeFavorite(recipeId)
            } else {
                repository.addFavorite(recipeId)
            }
            loadFavorites()
        }
    }
}
