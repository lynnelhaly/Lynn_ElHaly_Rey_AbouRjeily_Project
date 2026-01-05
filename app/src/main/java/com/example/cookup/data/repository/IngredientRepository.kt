package com.example.cookup.data.repository

import com.example.cookup.data.remote.model.IngredientDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class IngredientRepository(
    private val firestore: FirebaseFirestore
) {

    suspend fun getIngredients(): List<IngredientDto> {
        return firestore
            .collection("ingredients")
            .get()
            .await()
            .toObjects(IngredientDto::class.java)
    }
}
