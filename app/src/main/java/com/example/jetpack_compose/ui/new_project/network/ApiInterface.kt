package com.example.jetpack_compose.ui.new_project.network

import com.example.jetpack_compose.ui.new_project.model.MovieDetailResponse
import com.example.jetpack_compose.ui.new_project.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("/3/movie/popular")
    suspend fun getMovieList(
        @Query("api_key") apiKey: String = "66375beef771f456fb280068ea486edf"
    ) : MovieListResponse

    @GET("/3/movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id : String ,
        @Query("api_key") apiKey: String = "66375beef771f456fb280068ea486edf"
    ) : MovieDetailResponse
}