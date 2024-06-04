package com.example.statehardex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen(viewModel: MainViewModel = viewModel()) {
    val text1: MutableState<String> = remember {
        mutableStateOf("Hello World")
    }

    var text2: String by remember {
        mutableStateOf("Hello World")
    }

    val (text: String, setText: (String) -> Unit) = remember {
        mutableStateOf("Hello World")
    }

    Column {
        Text("Hello World!")
        Button(onClick = {
            text1.value = "변경"
            text2 = "변경"
            setText("변경")
            viewModel.changeValue("변경")
        }) {
            Text(text = "클릭")
        }
        TextField(value = text, onValueChange = setText)
    }
}

class MainViewModel : ViewModel() {
    private val _value = mutableStateOf("Hello World")
    val value: State<String> = _value

    fun changeValue(value: String){
        _value.value = "변경"
    }
}