package com.ahoy.myapplication.repository

import com.ahoy.myapplication.model.Movie

interface GetMoviesRepo {
    fun getAllMovies() : List<Movie>
}