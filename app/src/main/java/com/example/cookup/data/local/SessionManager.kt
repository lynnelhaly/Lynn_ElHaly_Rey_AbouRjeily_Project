package com.example.cookup.data.local

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

private val Context.dataStore by preferencesDataStore(name = "user_session")

class SessionManager(private val context: Context) {

    companion object {
        val KEY_TOKEN = stringPreferencesKey("token")
        val KEY_USER_ID = intPreferencesKey("user_id")
        val KEY_NAME = stringPreferencesKey("name")
    }

    val tokenFlow: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[KEY_TOKEN]
    }

    val nameFlow: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[KEY_NAME]
    }

    val userIdFlow: Flow<Int?> = context.dataStore.data.map { prefs ->
        prefs[KEY_USER_ID]
    }

    suspend fun saveSession(token: String, userId: Int, name: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_TOKEN] = token
            prefs[KEY_USER_ID] = userId
            prefs[KEY_NAME] = name
        }
    }

    suspend fun clearSession() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }

    fun getToken(): String? = runBlocking {
        context.dataStore.data.first()[KEY_TOKEN]
    }

    fun getUserName(): String? = runBlocking {
        context.dataStore.data.first()[KEY_NAME]
    }

    fun getUserId(): Int? = runBlocking {
        context.dataStore.data.first()[KEY_USER_ID]
    }

    fun isLoggedIn(): Boolean = getToken() != null
}
