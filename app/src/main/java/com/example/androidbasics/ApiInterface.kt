package com.example.androidbasics

import retrofit2.http.GET

interface ApiInterface {

    @GET("/users")
    suspend fun getUsers() : UserResponse

}