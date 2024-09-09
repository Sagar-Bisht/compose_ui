package com.example.jetpack_compose.ui.new_project.data

import com.example.jetpack_compose.ui.new_project.common.Resource
import com.example.jetpack_compose.ui.new_project.model.Movie
import com.example.jetpack_compose.ui.new_project.model.MovieDetailResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieDataSource: MovieDataSource) {

    suspend fun getMovieList(): Resource<List<Movie>> {
        return try {
            val response = movieDataSource.getMovieList()
            Resource.Success(data = response.results)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        }
    }

    suspend fun getMovieDetails(id : String) : Resource<MovieDetailResponse>{
        return try {
            val response = movieDataSource.getMovieDetail(id)
            Resource.Success(data = response)
        }catch (e : Exception){
            Resource.Error(message = e.message.toString())
        }
    }
}