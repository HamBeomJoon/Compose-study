package com.example.remembercorutinescope

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            var isFirstScreen by remember { mutableStateOf(true) }

            Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) {
                if (isFirstScreen) {
                    FirstScreen(
                        snackbarHostState = snackbarHostState,
                        onScreenChange = {
                            isFirstScreen = false
                        },
//                        coroutineScope = lifecycleScope
                    )
                } else {
                    SecondScreen()
                }
            }
        }
    }
}