package com.example.mviproj.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mviproj.data.Movie
import com.example.mviproj.ui.theme.MviProjTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MviProjTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieScreen(mainViewModel = viewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieScreenPreview() {
    MviProjTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            // Here, we provide mock data to preview the MovieScreen
            val mockMovies = listOf(
                Movie(id = 1, title = "Inception", year = "2010"),
                Movie(id = 2, title = "The Dark Knight", year = "2008"),
                Movie(id = 3, title = "Interstellar", year = "2014")
            )
            MovieScreenPreviewContent(movies = mockMovies)
        }
    }
}

@Composable
fun MovieScreenPreviewContent(movies: List<Movie>) {
//    LazyColumn(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        items(movies) { movie ->
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 4.dp, vertical = 8.dp)
//                    .shadow(4.dp, RoundedCornerShape(8.dp))
//            ) {
//                Column(
//                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
//                ) {
//                    Text(text = "Movie: ${movie.title}", modifier = Modifier.padding(top = 4.dp))
//                    Text(text = "Year: ${movie.year}", modifier = Modifier.padding(vertical = 4.dp))
//                }
//            }
//        }
//    }
    MoviesList(movies = movies)
}