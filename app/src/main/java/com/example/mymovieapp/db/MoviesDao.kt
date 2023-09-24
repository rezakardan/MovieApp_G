package com.example.mymovieapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mymovieapp.utils.Constants

@Dao
interface MoviesDao {

    @Insert
    suspend fun insertMovie(entity: MovieEntity)


    @Delete
    suspend fun deleteMovie(entity: MovieEntity)

    @Query("SELECT * FROM ${Constants.MOVIES_TABLE}")
    suspend fun getAllMovies():MutableList<MovieEntity>


    @Query("SELECT EXISTS (SELECT 1 FROM ${Constants.MOVIES_TABLE} WHERE id = :movieId)")
    suspend fun existMovie(movieId:Int):Boolean



}