package com.example.mymovieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.models.home.ResponseTopMoviesList
import com.example.mymovieapp.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepo:SearchRepository):ViewModel() {

    val progressLoading=MutableLiveData<Boolean>()

    val movieList=MutableLiveData<ResponseTopMoviesList>()

    val empty=MutableLiveData<Boolean>()



    fun searching(name:String)=viewModelScope.launch {


        progressLoading.postValue(true)
        val response=searchRepo.SearchMovies(name)

        if (response.isSuccessful){

            if (response.body()?.data!!.isNotEmpty()){

                movieList.postValue(response.body())

                empty.postValue(false)

            }else{

                empty.postValue(true)





        }
        }

        progressLoading.postValue(false)



    }




}