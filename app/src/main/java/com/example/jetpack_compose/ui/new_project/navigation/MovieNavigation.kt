package com.example.jetpack_compose.ui.new_project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpack_compose.ui.new_project.ui_layer.movie.MovieListScreen
import com.example.jetpack_compose.ui.new_project.ui_layer.details.MovieDetailScreen

@Composable
fun MovieNavigation(navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = MovieNavigationItem.MovieList.route
    ) {

        composable(MovieNavigationItem.MovieList.route) {
            MovieListScreen(navHostController)
        }

        composable(MovieNavigationItem.MovieDetails.route+"/{id}") {
            val id = it.arguments?.getString("id")
            MovieDetailScreen()
        }
    }
}