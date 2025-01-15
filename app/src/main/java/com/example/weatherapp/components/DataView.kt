package com.example.weatherapp.components

import androidx.compose.runtime.Composable

@Composable
fun DataView(
    isLoading: Boolean,
    error: String?,
    errorAction: () -> Unit,
    view: @Composable () -> Unit)
{
    if (isLoading) {
        LoadingView()
    }
    else if (error != null) {
        ErrorView(message = error, onClick = errorAction)
    }
    else {
        view()
    }
}