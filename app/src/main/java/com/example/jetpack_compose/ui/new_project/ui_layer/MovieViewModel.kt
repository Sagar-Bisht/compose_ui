package com.example.jetpack_compose.ui.new_project.ui_layer

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
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
    savedStateHandle: SavedStateHandle,
    private val movieRepository: MovieRepository
) :
    ViewModel() {

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
                Log.d("getMovieList: ", result.data.toString())
                movieList.value = MovieStateHolder(data = result.data)
            }

            is Resource.Error -> {
                Log.d("getMovieList2: ", result.data.toString())
                movieList.value = MovieStateHolder(error = result.message.toString())
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
                    Log.d("getMovieListDeta: ", result.data.toString())
                    movieDetailList.value = MovieDetailStateHolder(data = result.data)
                }

                is Resource.Error -> {
                    Log.d("getMovieListDeta2: ", result.data.toString())
                    movieDetailList.value =
                        MovieDetailStateHolder(error = result.message.toString())
                }
            }
        }
    }

}