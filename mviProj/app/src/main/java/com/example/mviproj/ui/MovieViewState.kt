package com.example.mviproj.ui

import com.example.mviproj.data.Movie

/** Theis represent the view state on the screen,
 * whether it's loading or success fetching the data or an error occur.
 */
data class MovieViewState(
    val loading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null,
)