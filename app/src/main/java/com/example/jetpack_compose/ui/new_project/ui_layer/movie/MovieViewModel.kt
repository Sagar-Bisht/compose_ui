package com.example.jetpack_compose.ui.new_project.ui_layer.movie

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose.ui.new_project.common.Resource
import com.example.jetpack_compose.ui.new_project.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val movieList = mutableStateOf(MovieStateHolder())

    init {
        getMovieList()
    }

    private fun getMovieList() = viewModelScope.launch(Dispatchers.IO) {
        when (val result = movieRepository.getMovieList()) {
            is Resource.Loading -> {
                movieList.value = MovieStateHolder(isLoading = true)
            }

            is Resource.Success -> {
                movieList.value = MovieStateHolder(data = result.data)
            }

            is Resource.Error -> {
                movieList.value = MovieStateHolder(error = result.message.toString())
            }
        }
    }

}