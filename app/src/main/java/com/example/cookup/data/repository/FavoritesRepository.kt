package com.example.cookup.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FavoritesRepository(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) {

    private fun userFavoritesCollection() =
        firestore
            .collection("users")
            .document(auth.currentUser!!.uid)
            .collection("favorites")

    suspend fun addFavorite(recipeId: Int) {
        val data = mapOf("recipeId" to recipeId)
        userFavoritesCollection()
            .document(recipeId.toString())
            .set(data)
            .await()
    }

    suspend fun removeFavorite(recipeId: Int) {
        userFavoritesCollection()
            .document(recipeId.toString())
            .delete()
            .await()
    }

    suspend fun getFavoriteRecipeIds(): List<Int> {
        return userFavoritesCollection()
            .get()
            .await()
            .documents
            .mapNotNull { it.getLong("recipeId")?.toInt() }
    }

    suspend fun isFavorite(recipeId: Int): Boolean {
        val doc = userFavoritesCollection()
            .document(recipeId.toString())
            .get()
            .await()
        return doc.exists()
    }
}
