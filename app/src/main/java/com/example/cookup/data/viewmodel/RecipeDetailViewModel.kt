package com.example.cookup.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookup.data.CookUpRepository
import com.example.cookup.data.model.RecipeEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeDetailViewModel(
    private val repo: CookUpRepository,
    private val recipeId: Int
) : ViewModel() {

    private val _recipe = MutableStateFlow<RecipeEntity?>(null)
    val recipe: StateFlow<RecipeEntity?> = _recipe

    init {
        loadRecipe()
    }

    private fun loadRecipe() {
        viewModelScope.launch {
            _recipe.value = repo.getRecipeById(recipeId)
        }
    }

    fun toggleFavorite(isFav: Boolean) {
        viewModelScope.launch {
            repo.toggleFavorite(recipeId, isFav)
            loadRecipe()   // reload updated recipe
        }
    }
}
