package com.example.jetpack_compose.ui.new_project.ui_layer.details

import com.example.jetpack_compose.ui.new_project.model.MovieDetailResponse

data class MovieDetailStateHolder(
    val data : MovieDetailResponse? = null,
    val error: String = "",
    val isLoading : Boolean = false
)