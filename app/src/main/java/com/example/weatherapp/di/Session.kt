package com.example.weatherapp.di

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Session @Inject constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        private const val THEME = "dark_mode"
        private const val UNITS = "units"
        val theme = booleanPreferencesKey(THEME)
        val units = stringPreferencesKey(UNITS)
    }

    fun getTheme(): Flow<Boolean> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map { preferences ->
            preferences[theme] ?: false
        }
    }

    suspend fun setTheme(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[theme] = value
        }
    }

    fun getUnits(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map { preferences ->
            Log.d("LUKA", "getUnits: ${preferences[units]}")
            preferences[units] ?: "metric"
        }
    }

    suspend fun setUnits(value: String) {
        dataStore.edit { preferences ->
            preferences[units] = value
        }
    }
}