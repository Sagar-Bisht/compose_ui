package com.example.jetpack_compose.ui.new_project.ui_layer.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun MovieDetailScreen(viewModel: MovieDetailViewModel = hiltViewModel()) {

    val result = viewModel.movieDetailList.value

    if (result.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    if (result.error.isNotBlank()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Text(text = result.error)
        }
    }

    result.data?.let { movieDetail ->
        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movieDetail.poster_path}",
                contentDescription = null,
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = movieDetail.title,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = movieDetail.tagline,
                style = MaterialTheme.typography.displayMedium,
                fontSize = 16.sp
            )
        }
    }

}