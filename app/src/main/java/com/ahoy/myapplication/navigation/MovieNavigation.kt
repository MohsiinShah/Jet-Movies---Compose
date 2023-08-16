package com.ahoy.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahoy.myapplication.model.Movie
import com.ahoy.myapplication.model.MovieArgType
import com.ahoy.myapplication.screens.detail.DetailsScreen
import com.ahoy.myapplication.screens.home.HomeScreen
import com.google.gson.Gson

@Composable
fun MovieNavigation(movie: Movie?){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name){
        composable(route = MovieScreens.HomeScreen.name){
            HomeScreen(navController = navController)
        }

        composable(route = "DetailsScreen/{movie}",
            arguments = listOf(navArgument("movie"){
                type = MovieArgType()
            })
        ) { navBackStackEntry->
            val movie = navBackStackEntry.arguments?.getString("movie")?.let { Gson().fromJson(it, Movie::class.java) }
            DetailsScreen(navController, movie)
        }
    }
}