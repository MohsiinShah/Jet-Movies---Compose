package com.ahoy.myapplication.screens.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahoy.myapplication.model.Movie
import com.ahoy.myapplication.repository.GetMoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository : GetMoviesRepo
): ViewModel() {

    private val _moviesList = MutableLiveData<List<Movie>>()
    val moviesList : LiveData<List<Movie>> = _moviesList
    init {
        getMovies()
    }

    private fun getMovies(){
        _moviesList.postValue(repository.getAllMovies())
        Log.i("movies", "getMovies:${repository.getAllMovies()} ")
    }

}