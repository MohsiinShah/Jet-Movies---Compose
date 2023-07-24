package com.ahoy.myapplication.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ahoy.myapplication.MyApp
import com.ahoy.myapplication.model.Movie
import com.ahoy.myapplication.navigation.MovieNavigation
import com.ahoy.myapplication.navigation.MovieScreens
import com.ahoy.myapplication.widgets.MovieRow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.LightGray,
            elevation = 5.dp
        ) {
            Text(
                text = "Movies", Modifier.padding(start = 6.dp), textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }) {
        MainContent(navController)
    }
}


@Composable
fun MainContent(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    val moviesList: List<Movie> by viewModel.moviesList.observeAsState(emptyList())
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(moviesList) {
                MovieRow(movie = it) { movie ->
                    navController.navigate(route = MovieScreens.DetailsScreen.name + "/$movie")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MovieNavigation()
    }
}