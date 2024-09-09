package com.example.jetpack_compose.ui.new_project.ui_layer

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MovieDetailScreen(viewModel: MovieViewModel = hiltViewModel()){

    val result = viewModel.movieDetailList.value

    if(result.isLoading){

    }

    if(result.error.isNotBlank()){

    }

    result.data?.let { movieDetail ->

    }

}