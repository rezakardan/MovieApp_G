package com.example.mymovieapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [MovieEntity::class], version = 2, exportSchema = false)
abstract class MoviesDatabase():RoomDatabase() {

    abstract fun movieDao():MoviesDao


}