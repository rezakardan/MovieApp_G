package com.example.mymovieapp.models.detail


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ResponseDetailMovies(
    @SerializedName("actors")
    val actors: String?, // Edward Norton, Brad Pitt, Meat Loaf, Zach Grenier
    @SerializedName("awards")
    val awards: String?, // Nominated for 1 Oscar. Another 10 wins & 31 nominations.
    @SerializedName("country")
    val country: String?, // USA, Germany
    @SerializedName("director")
    val director: String?, // David Fincher
    @SerializedName("genres")
    val genres: List<String?>?,
    @SerializedName("id")
    val id: Int?, // 10
    @SerializedName("images")
    val images: List<String?>?,
    @SerializedName("imdb_id")
    val imdbId: String?, // tt0137523
    @SerializedName("imdb_rating")
    val imdbRating: String?, // 8.8
    @SerializedName("imdb_votes")
    val imdbVotes: String?, // 1,384,393
    @SerializedName("metascore")
    val metascore: String?, // 66
    @SerializedName("plot")
    val plot: String?, // An insomniac office worker, looking for a way to change his life, crosses paths with a devil-may-care soap maker, forming an underground fight club that evolves into something much, much more.
    @SerializedName("poster")
    val poster: String?, // https://moviesapi.ir/images/tt0137523_poster.jpg
    @SerializedName("rated")
    val rated: String?, // R
    @SerializedName("released")
    val released: String?, // 15 Oct 1999
    @SerializedName("runtime")
    val runtime: String?, // 139 min
    @SerializedName("title")
    val title: String?, // Fight Club
    @SerializedName("type")
    val type: String?, // movie
    @SerializedName("writer")
    val writer: String?, // Chuck Palahniuk (novel), Jim Uhls (screenplay)
    @SerializedName("year")
    val year: String? // 1999
) : Parcelable