package com.example.mymovieapp.api

import com.example.mymovieapp.models.detail.ResponseDetailMovies
import com.example.mymovieapp.models.home.ResponseGnerMovies
import com.example.mymovieapp.models.home.ResponseTopMoviesList
import com.example.mymovieapp.models.register.BodyRegister
import com.example.mymovieapp.models.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {
    @POST("register")
    suspend fun registerUser(@Body body: BodyRegister): Response<ResponseRegister>


    @GET("genres/{genre_id}/movies")
suspend fun topMoviesList(@Path("genre_id")genreId:Int):Response<ResponseTopMoviesList>


@GET("genres")
suspend fun genreMovies():Response<ResponseGnerMovies>


@GET("movies")
suspend fun moviesLastList():Response<ResponseTopMoviesList>

@GET("movies")
suspend fun searchMovie(@Query("q")name:String):Response<ResponseTopMoviesList>

@GET("movies/{movie_id}")
suspend fun detailMovie(@Path("movie_id")id:Int):Response<ResponseDetailMovies>

}