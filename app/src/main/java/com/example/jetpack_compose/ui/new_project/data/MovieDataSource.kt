package com.example.jetpack_compose.ui.new_project.data

import com.example.jetpack_compose.ui.new_project.network.ApiInterface
import javax.inject.Inject

class MovieDataSource @Inject constructor(private val apiInterface: ApiInterface){

    suspend fun getMovieList() = apiInterface.getMovieList()

    suspend fun getMovieDetail(id : String) = apiInterface.getMovieDetail(id)

}