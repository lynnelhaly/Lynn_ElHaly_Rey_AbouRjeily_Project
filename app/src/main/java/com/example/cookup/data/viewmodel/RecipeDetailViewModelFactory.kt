package com.example.cookup.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookup.data.CookUpRepository

class RecipeDetailViewModelFactory(
    private val repo: CookUpRepository,
    private val recipeId: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeDetailViewModel(repo, recipeId) as T
    }
}
