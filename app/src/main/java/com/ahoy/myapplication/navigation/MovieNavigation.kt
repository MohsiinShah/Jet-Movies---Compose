package com.ahoy.myapplication.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahoy.myapplication.screens.detail.DetailsScreen
import com.ahoy.myapplication.screens.home.HomeScreen

@Composable
fun MovieNavigation( context : ComponentActivity){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name){
        composable(route = MovieScreens.HomeScreen.name){
            HomeScreen(navController = navController, context = context)
        }

        composable(route = MovieScreens.DetailsScreen.name+"/{movie}",
        arguments = listOf(navArgument(name = "movie") {type = NavType.StringType})){
            backStackEntry ->
            DetailsScreen(navController = navController, backStackEntry.arguments?.getString("movie"))
        }
    }
}