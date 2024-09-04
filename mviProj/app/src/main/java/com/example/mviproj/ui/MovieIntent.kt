package com.example.mviproj.ui

/** Represent the user interactions like
 * . button clicks,
 * . text input
 * . or fetching data from the server or local DB,  whether initiated by the user or the app itself.
 */
sealed class MovieIntent {
    data object LoadMovies : MovieIntent()
}