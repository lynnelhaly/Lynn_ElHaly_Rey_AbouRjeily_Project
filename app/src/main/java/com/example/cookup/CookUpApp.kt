package com.example.cookup

import android.app.Application
import com.example.cookup.data.repository.AuthRepository
import com.example.cookup.data.repository.FavoritesRepository
import com.example.cookup.data.repository.IngredientRepository
import com.example.cookup.data.repository.RecipeRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CookUpApp : Application() {

    lateinit var authRepository: AuthRepository
    lateinit var ingredientRepository: IngredientRepository
    lateinit var recipeRepository: RecipeRepository
    lateinit var favoritesRepository: FavoritesRepository

    override fun onCreate() {
        super.onCreate()

        val firestore = FirebaseFirestore.getInstance()
        val auth = FirebaseAuth.getInstance()

        authRepository = AuthRepository(auth)
        ingredientRepository = IngredientRepository(firestore)
        recipeRepository = RecipeRepository(firestore)
        favoritesRepository = FavoritesRepository(firestore, auth)
    }
}
