package com.example.jetpack_compose.ui.new_project.di

import com.example.jetpack_compose.ui.new_project.data.MovieDataSource
import com.example.jetpack_compose.ui.new_project.data.MovieRepository
import com.example.jetpack_compose.ui.new_project.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {


    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideBaseUrl(): String = "https://api.themoviedb.org"

    @Provides
    fun retrofitInstance(baseUrl: String, okhttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)

    @Provides
    fun provideMovieDatasource(apiInterface: ApiInterface) = MovieDataSource(apiInterface)

    @Provides
    fun movieRepository(movieDataSource: MovieDataSource) = MovieRepository(movieDataSource)

}