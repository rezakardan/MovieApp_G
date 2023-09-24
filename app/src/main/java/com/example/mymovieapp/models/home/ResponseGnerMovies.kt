package com.example.mymovieapp.models.home


import com.google.gson.annotations.SerializedName

class ResponseGnerMovies : ArrayList<ResponseGnerMovies.ResponseGnerMoviesItem>(){

    data class ResponseGnerMoviesItem(
        @SerializedName("id")
        val id: Int?, // 1
        @SerializedName("name")
        val name: String? // Crime
    )
}