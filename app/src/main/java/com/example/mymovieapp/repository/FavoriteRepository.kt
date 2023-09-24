package com.example.mymovieapp.repository

import com.example.mymovieapp.db.MovieEntity
import com.example.mymovieapp.db.MoviesDao
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val movieDao:MoviesDao) {


    suspend fun insert(entity: MovieEntity)=movieDao.insertMovie(entity)


    suspend fun delete(entity: MovieEntity)=movieDao.deleteMovie(entity)

    suspend fun getAllMovieData()=movieDao.getAllMovies()

    suspend fun exist(movieId:Int)=movieDao.existMovie(movieId)

}