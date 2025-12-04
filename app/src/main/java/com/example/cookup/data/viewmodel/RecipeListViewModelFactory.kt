package com.example.cookup.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookup.data.CookUpRepository

class RecipeListViewModelFactory(
    private val repo: CookUpRepository,
    private val ids: List<Int>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeListViewModel(repo, ids) as T
    }
}
