package com.example.cookup.data.repository

import com.example.cookup.data.remote.model.RecipeDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class RecipeRepository(
    private val firestore: FirebaseFirestore
) {

    suspend fun getRecipes(): List<RecipeDto> {
        return firestore
            .collection("recipes")
            .get()
            .await()
            .toObjects(RecipeDto::class.java)
            .sortedBy { it.id }
    }
}
