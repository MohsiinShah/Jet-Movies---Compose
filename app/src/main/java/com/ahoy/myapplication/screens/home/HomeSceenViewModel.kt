package com.ahoy.myapplication.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahoy.myapplication.model.Movie
import com.ahoy.myapplication.repository.GetMoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    val repository : GetMoviesRepo
): ViewModel() {

    val _moviesList = MutableLiveData<List<Movie>>()
    val moviesList : LiveData<List<Movie>> = _moviesList

    fun getMovies(){
        _moviesList.postValue(repository.getAllMovies())
    }

}