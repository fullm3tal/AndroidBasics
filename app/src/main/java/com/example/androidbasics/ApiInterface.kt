package com.example.androidbasics

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/users")
    fun getUsers() : Call<UserRespnse>

}