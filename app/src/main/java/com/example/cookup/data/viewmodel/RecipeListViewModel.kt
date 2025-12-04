package com.example.cookup.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookup.data.CookUpRepository
import com.example.cookup.data.model.RecipeWithMatchCount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeListViewModel(
    private val repo: CookUpRepository,
    private val ingredientIds: List<Int>
) : ViewModel() {

    private val _recipes = MutableStateFlow<List<RecipeWithMatchCount>>(emptyList())
    val recipes: StateFlow<List<RecipeWithMatchCount>> = _recipes

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        viewModelScope.launch {
            _recipes.value = repo.getRecipesMatchingIngredients(ingredientIds)
        }
    }
}
