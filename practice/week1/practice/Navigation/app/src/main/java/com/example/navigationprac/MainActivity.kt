package com.example.navigationprac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.navigationprac.navigation.NavGraph
import com.example.navigationprac.ui.theme.NavigationPracTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
private fun MainScreen() {
    NavigationPracTheme {
        val navController = rememberNavController()
        NavGraph(navController)
    }
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
private fun MainScreenPreview() {
    NavigationPracTheme {
        MainScreen()
    }
}