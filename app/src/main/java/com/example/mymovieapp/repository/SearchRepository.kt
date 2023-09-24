package com.example.mymovieapp.repository

import com.example.mymovieapp.api.ApiServices
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api:ApiServices) {

    suspend fun SearchMovies(name:String)=api.searchMovie(name)


}