package com.example.mymovieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.db.MovieEntity
import com.example.mymovieapp.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteRepo:FavoriteRepository):ViewModel() {

    val listOfMovie=MutableLiveData<List<MovieEntity>>()

    val emptyList=MutableLiveData<Boolean>()


    fun favoriteMovie()=viewModelScope.launch {

        val list=favoriteRepo.getAllMovieData()


        if (list.isNotEmpty()){

            listOfMovie.postValue(list)

            emptyList.postValue(false)





        }else{


            emptyList.postValue(true)
        }




    }








}