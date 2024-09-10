package com.example.jetpack_compose.ui.new_project.ui_layer.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose.ui.new_project.common.Resource
import com.example.jetpack_compose.ui.new_project.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle,
) :
    ViewModel() {

    init {
        viewModelScope.launch {
            savedStateHandle.getStateFlow("id", "0").collectLatest {
                getMovieDetail(it)
            }
        }
    }

    val movieDetailList = mutableStateOf(MovieDetailStateHolder())

    private fun getMovieDetail(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = movieRepository.getMovieDetails(id)) {
                is Resource.Loading -> {
                    movieDetailList.value = MovieDetailStateHolder(isLoading = true)
                }

                is Resource.Success -> {
                    movieDetailList.value = MovieDetailStateHolder(data = result.data)
                }

                is Resource.Error -> {
                    movieDetailList.value = MovieDetailStateHolder(error = result.message.toString())
                }
            }
        }
    }
}