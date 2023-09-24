package com.example.mymovieapp.repository

import com.example.mymovieapp.api.ApiServices
import com.example.mymovieapp.models.register.BodyRegister
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val api: ApiServices) {
    suspend fun registerUser(body: BodyRegister) = api.registerUser(body)
}