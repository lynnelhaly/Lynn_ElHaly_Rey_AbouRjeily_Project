package com.example.cookup.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookup.data.CookUpRepository
import com.example.cookup.data.model.IngredientEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IngredientViewModel(
    private val repo: CookUpRepository
) : ViewModel() {

    private val _allIngredients = MutableStateFlow<List<IngredientEntity>>(emptyList())
    val allIngredients: StateFlow<List<IngredientEntity>> = _allIngredients

    private val _selectedIngredients = MutableStateFlow<List<IngredientEntity>>(emptyList())
    val selectedIngredients: StateFlow<List<IngredientEntity>> = _selectedIngredients

    fun loadIngredients() {
        viewModelScope.launch {
            _allIngredients.value = repo.getAllIngredients()
        }
    }

    fun toggleIngredient(ingredient: IngredientEntity) {
        val current = _selectedIngredients.value.toMutableList()
        if (current.contains(ingredient))
            current.remove(ingredient)
        else
            current.add(ingredient)

        _selectedIngredients.value = current
    }

    fun removeIngredient(ingredient: IngredientEntity) {
        val current = _selectedIngredients.value.toMutableList()
        current.remove(ingredient)
        _selectedIngredients.value = current
    }

    fun clearSelected() {
        _selectedIngredients.value = emptyList()
    }
}
