package com.example.mymovieapp.repository

import com.example.mymovieapp.api.ApiServices
import com.example.mymovieapp.db.MovieEntity
import com.example.mymovieapp.db.MoviesDao
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api:ApiServices,private val dao:MoviesDao) {


    suspend fun detailLoadingMovies(id:Int)=api.detailMovie(id)





    suspend fun insert(entity: MovieEntity)=dao.insertMovie(entity)


    suspend fun delete(entity: MovieEntity)=dao.deleteMovie(entity)

    suspend fun exist(id:Int)=dao.existMovie(id)





}