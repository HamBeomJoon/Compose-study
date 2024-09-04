package com.example.mviproj.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mviproj.data.Movie

@Composable
fun MovieScreen(mainViewModel: MainViewModel) {
    val state by mainViewModel.state.collectAsState()

    LaunchedEffect(mainViewModel) {
        // Composable이 처음 로드될 때 영화 데이터를 가져옴
        mainViewModel.handleIntent(MovieIntent.LoadMovies)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            state.loading -> {
                // 로딩 중일 때 표시할 인디케이터
                CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
            }

            state.error != null -> {
                Text(text = "Error: ${state.error}", color = Color.Red)
            }

            else -> {
                MoviesList(movies = state.movies)
            }
        }
    }
}

@Composable
fun MoviesList(movies: List<Movie>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(movies) { movie ->
            MovieItem(movie)
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .shadow(4.dp, RoundedCornerShape(8.dp))
    ) {
        Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
            Text(text = "Movie: ${movie.title}", modifier = Modifier.padding(top = 4.dp))
            Text(text = "Year: ${movie.year}", modifier = Modifier.padding(vertical = 4.dp))
        }
    }
}