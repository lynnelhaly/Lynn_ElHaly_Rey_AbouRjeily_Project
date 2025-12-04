package com.example.cookup.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookup.data.CookUpRepository
import com.example.cookup.data.model.RecipeEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repo: CookUpRepository
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<RecipeEntity>>(emptyList())
    val favorites: StateFlow<List<RecipeEntity>> = _favorites

    init {
        loadFavorites()
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _favorites.value = repo.getFavoriteRecipes()
        }
    }
}
