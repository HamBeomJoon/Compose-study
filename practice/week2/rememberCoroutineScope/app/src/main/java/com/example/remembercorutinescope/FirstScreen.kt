package com.example.remembercorutinescope

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun FirstScreen(
    snackbarHostState: SnackbarHostState,
    onScreenChange: () -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope() // 인자가 안넘어오면 rememberCoroutineScope 사용
) {
    Column() {
        Button(
            onClick = {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Show Snackbar!")
                }
            }
        ) {
            Text("Show Snackbar")
        }
        Button(
            onClick = {
                onScreenChange()
            }
        ) {
            Text("Navigate to another Screen")
        }
    }
}