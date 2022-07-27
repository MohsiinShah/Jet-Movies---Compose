package com.ahoy.myapplication.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.ahoy.myapplication.model.Movie
import com.ahoy.myapplication.model.getMovies
import com.ahoy.myapplication.screens.home.MainContent
import com.ahoy.myapplication.widgets.MovieRow

@Composable
fun DetailsScreen(navController: NavController, movieID: String?) {

    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.LightGray,
            elevation = 5.dp
        ) {
            
            Row {
               Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "arrow_back",
               modifier = Modifier
                   .clickable {
                       navController.popBackStack()
                   }
                   .padding(end = 10.dp))

                Text(text = "Detail", textAlign = TextAlign.Center,
                color = Color.Black)

            }
        }
    }) {
        DetailsScreenContent(movieID = movieID)
    }
}

@Composable
fun DetailsScreenContent(movieID: String?){
        Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val movie = getMovies().single {movie -> movie.id == movieID }

            Column {
                MovieRow(movie = movie)
            }

            Divider(modifier = Modifier.padding(top = 6.dp))

            Text(text = "${movie.title} Images",Modifier.padding(6.dp) ,style = MaterialTheme.typography.subtitle2)

            HorizontalScrollableImageView(movie)
        }
    }
}

@Composable
private fun HorizontalScrollableImageView(movie: Movie) {
    LazyRow() {
        items(movie.images) { it ->

            Card(
                modifier = Modifier
                    .size(240.dp)
                    .padding(6.dp),
                elevation = 0.dp,
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(it)
                            .crossfade(true)
                            .build()
                    ), contentDescription = "Movie Image", contentScale = ContentScale.Fit,
                    alignment = Alignment.TopCenter,  modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}