package com.example.mymovieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.models.home.ResponseGnerMovies
import com.example.mymovieapp.models.home.ResponseTopMoviesList
import com.example.mymovieapp.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepo:HomeRepository):ViewModel() {


    val movieslistTop=MutableLiveData<ResponseTopMoviesList>()

val genreMovieeeeee=MutableLiveData<ResponseGnerMovies>()

    val lastMovies=MutableLiveData<ResponseTopMoviesList>()


    val progressBarLastMovie=MutableLiveData<Boolean>()


    fun topMoviesList(genreId:Int)=viewModelScope.launch {

      val response=  homeRepo.listOfMoviesTop(genreId)

if (response.isSuccessful){


    movieslistTop.postValue(response.body())



}

    }



    fun genreMovies()=viewModelScope.launch {



    val response=    homeRepo.genreListMovies()

      if (response.isSuccessful){


          genreMovieeeeee.postValue(response.body())


      }






    }


    fun lastMovie()=viewModelScope.launch {

        progressBarLastMovie.postValue(true)
       val response= homeRepo.lastMovies()

        if (response.isSuccessful){

            lastMovies.postValue(response.body())


        }
        progressBarLastMovie.postValue(false)


    }

















}