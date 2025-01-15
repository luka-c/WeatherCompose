package com.example.weatherapp.pages.settings

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.di.Session
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val session: Session) : ViewModel() {

    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme

    private val _units = MutableStateFlow("metric")
    val units: StateFlow<String> = _units

    init {
        viewModelScope.launch {
            session.getTheme().collect {
                _isDarkTheme.value = it
            }
        }

        viewModelScope.launch {
            session.getUnits().collect {
                _units.value = it
            }
        }
    }

    fun setTheme(value: Boolean) = viewModelScope.launch {
        session.setTheme(value)
    }

    fun setUnits(value: String) = viewModelScope.launch {
        session.setUnits(value)
    }
}