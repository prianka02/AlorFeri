package com.test.alorferi.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatastoreManager(
     private val context: Context
) {
    private val Context.dataStore by preferencesDataStore(name = "AlorFeriDataStore")

    // Save token
    suspend fun saveToken(token: String) {
        val tokenKey = stringPreferencesKey(DataStoreKeys.TOKEN)
        val isLoggedInKey = booleanPreferencesKey(DataStoreKeys.IS_LOGGED_IN)

        context.dataStore.edit { preferences ->
            preferences[tokenKey] = token
            preferences[isLoggedInKey] = true // Mark the user as logged in
        }
    }

    // Retrieve token
    fun getToken(): Flow<String?> {
        val tokenKey = stringPreferencesKey(DataStoreKeys.TOKEN)
        return context.dataStore.data.map { preferences ->
            preferences[tokenKey]
        }
    }

    // Save isLoggedIn status
    suspend fun setIsLoggedIn(isLoggedIn: Boolean) {
        val isLoggedInKey = booleanPreferencesKey(DataStoreKeys.IS_LOGGED_IN)
        context.dataStore.edit { preferences ->
            preferences[isLoggedInKey] = isLoggedIn
        }
    }

    // Retrieve isLoggedIn status
    fun isLoggedIn(): Flow<Boolean> {
        val isLoggedInKey = booleanPreferencesKey(DataStoreKeys.IS_LOGGED_IN)
        return context.dataStore.data.map { preferences ->
            preferences[isLoggedInKey] ?: false
        }
    }
}

