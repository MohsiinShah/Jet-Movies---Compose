package com.ahoy.myapplication.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahoy.myapplication.MyApp
import com.ahoy.myapplication.model.Movie
import com.ahoy.myapplication.model.getMovies
import com.ahoy.myapplication.navigation.MovieNavigation
import com.ahoy.myapplication.navigation.MovieScreens
import com.ahoy.myapplication.widgets.MovieRow


var selectedMovie: Movie? = null
@Composable
fun HomeScreen(navController: NavController){

    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.LightGray,
            elevation = 5.dp
        ) {
            Text(text = "Movies", Modifier.padding(start = 6.dp), textAlign = TextAlign.Center,
                color = Color.Black)
        }
    }) {
        MainContent(navController)
    }
}


@Composable
fun MainContent(
    navController: NavController,
    moviesList: List<Movie> = getMovies()
) {

    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = moviesList) { mov ->
                MovieRow(movie = mov){ movie ->
                    selectedMovie = mov
                    navController.navigate(route = MovieScreens.DetailsScreen.name+"/$selectedMovie")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MovieNavigation(selectedMovie)
    }
}