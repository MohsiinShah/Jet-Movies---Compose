package com.ahoy.myapplication.repository

import com.ahoy.myapplication.model.Movie
import com.ahoy.myapplication.model.getMovies

class GetMoviesRepoImpl : GetMoviesRepo {
    override fun getAllMovies(): List<Movie> {
        return getMovies()
    }
}