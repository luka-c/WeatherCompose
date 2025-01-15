package com.example.weatherapp.pages.settings

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.weatherapp.components.AppBar
import com.example.weatherapp.components.BottomBar

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { AppBar(
            title = "Settings",
            navController = navController
        ) },
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            SettingsContent(viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsContent(viewModel: SettingsViewModel) {
    val isDarkTheme = viewModel.isDarkTheme.collectAsStateWithLifecycle().value
    val currentUnits = viewModel.units.collectAsStateWithLifecycle().value
    val units = listOf("metric", "imperial")

    val expanded = remember {
        mutableStateOf(false)
    }

    val activity = LocalContext.current as? Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Force dark mode", style = MaterialTheme.typography.titleMedium)
            Switch(
                checked = isDarkTheme,
                onCheckedChange = {
                    viewModel.setTheme(!isDarkTheme)
                    activity?.recreate()
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Units", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(6.dp))

        ExposedDropdownMenuBox(
            expanded = expanded.value,
            onExpandedChange = { expanded.value = !expanded.value }
        ) {
            TextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                readOnly = true,
                value = currentUnits.uppercase(),
                onValueChange = { },
                trailingIcon = { TrailingIcon(expanded = expanded.value) }
            )

            DropdownMenu(
                modifier = Modifier.exposedDropdownSize(),
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false }
            ) {
                units.forEach {
                    DropdownMenuItem(
                        text = { Text(text = it.uppercase()) },
                        onClick = {
                            viewModel.setUnits(it)
                            expanded.value = false
                        }
                    )
                }
            }
        }
    }
}