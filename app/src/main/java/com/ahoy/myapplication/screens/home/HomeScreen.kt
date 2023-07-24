package com.ahoy.myapplication.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ahoy.myapplication.MyApp
import com.ahoy.myapplication.model.Movie
import com.ahoy.myapplication.navigation.MovieNavigation
import com.ahoy.myapplication.navigation.MovieScreens
import com.ahoy.myapplication.widgets.MovieRow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, context: ComponentActivity) {

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
        MainContent(navController,context)
    }
}


@Composable
fun MainContent(
    navController: NavController,
    context: ComponentActivity
) {
    val viewModel : HomeScreenViewModel = hiltViewModel()
    val moviesListState = remember { mutableStateOf<List<Movie>>(emptyList()) }

    LaunchedEffect(viewModel.moviesList) {
        viewModel.moviesList.observe(context) {
            moviesListState.value = it
            Log.i("movies", "MainContent: ${it.toString()}")
        }
    }
    Column(modifier = Modifier.padding(12.dp)) {

        LazyColumn {
            items(moviesListState.value) {
                MovieRow(movie = it) { movie ->
                    Log.i("movies", "MainContent: ${movie.toString()}")
                    navController.navigate(route = MovieScreens.DetailsScreen.name + "/$movie")
                }
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MovieNavigation()
    }
}*/
