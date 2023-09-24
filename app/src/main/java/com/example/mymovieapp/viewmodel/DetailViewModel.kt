package com.example.mymovieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.db.MovieEntity
import com.example.mymovieapp.models.detail.ResponseDetailMovies
import com.example.mymovieapp.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailRepo:DetailRepository):ViewModel(){


    val progressBarLoading=MutableLiveData<Boolean>()

    val listOfMovies=MutableLiveData<ResponseDetailMovies>()


    fun loadMoviesApi(id:Int)=viewModelScope.launch {

        progressBarLoading.postValue(true)

     val response=   detailRepo.detailLoadingMovies(id)

        if (response.isSuccessful){

            listOfMovies.postValue(response.body())



        }

        progressBarLoading.postValue(false)



    }



val isInDatabase=MutableLiveData<Boolean>()

suspend fun existInDatabase(id:Int)= withContext(viewModelScope.coroutineContext){detailRepo.exist(id)}


    fun favoriteMoviesDb(id:Int,entity: MovieEntity)=viewModelScope.launch {


        if (existInDatabase(id)){

            isInDatabase.postValue(false)

            detailRepo.delete(entity)



        }else{

            isInDatabase.postValue(true)

            detailRepo.insert(entity)

        }




    }








}