package com.example.weatherapp.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(valueState: MutableState<String>, onSearch: (String) -> Unit = {}) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = "Enter a city ") },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            capitalization = KeyboardCapitalization.Words
        ),
        keyboardActions = KeyboardActions(onSearch = {
            onSearch(valueState.value)
        })
    )
}