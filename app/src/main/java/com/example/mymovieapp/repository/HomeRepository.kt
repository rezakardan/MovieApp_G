package com.example.mymovieapp.repository

import com.example.mymovieapp.api.ApiServices
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api:ApiServices) {


suspend fun listOfMoviesTop(genreId:Int)=api.topMoviesList(genreId)

    suspend fun genreListMovies()=api.genreMovies()

    suspend fun lastMovies()=api.moviesLastList()




}