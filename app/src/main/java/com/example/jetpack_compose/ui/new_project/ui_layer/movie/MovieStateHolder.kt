package com.example.jetpack_compose.ui.new_project.ui_layer.movie

import com.example.jetpack_compose.ui.new_project.model.Movie
import com.example.jetpack_compose.ui.new_project.model.MovieDetailResponse

data class MovieStateHolder(
    val data : List<Movie>? = null,
    val error: String = "" ,
    val isLoading : Boolean = false
)

