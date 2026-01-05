package com.example.cookup.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookup.data.remote.model.IngredientDto
import com.example.cookup.data.repository.IngredientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class IngredientUiState(
    val ingredients: List<IngredientDto> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class IngredientViewModel(
    private val repository: IngredientRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(IngredientUiState())
    val uiState: StateFlow<IngredientUiState> = _uiState

    init {
        fetchIngredients()
    }

    private fun fetchIngredients() {
        viewModelScope.launch {
            _uiState.value = IngredientUiState(isLoading = true)

            try {
                val ingredients = repository.getIngredients()
                _uiState.value = IngredientUiState(
                    ingredients = ingredients,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = IngredientUiState(
                    isLoading = false,
                    errorMessage = e.message
                )
            }
        }
    }
}
