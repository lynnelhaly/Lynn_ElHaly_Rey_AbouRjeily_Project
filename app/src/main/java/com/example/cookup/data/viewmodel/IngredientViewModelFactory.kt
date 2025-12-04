package com.example.cookup.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookup.data.CookUpRepository

class IngredientViewModelFactory(
    private val repo: CookUpRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return IngredientViewModel(repo) as T
    }
}
