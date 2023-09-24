package com.example.mymovieapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mymovieapp.utils.Constants

@Entity(tableName = Constants.MOVIES_TABLE)
data class MovieEntity (
    @PrimaryKey
    var id:Int=0,
    var country:String="",
    var poster:String="",
    var year:String="",
    var name:String="",
    var rate:String=""





        )