package com.example.rest_api_retrofit_android.Rest

import com.example.rest_api_retrofit_android.Model.GitHubUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubUserEndPoints {

    @GET("/users/{user}")
    fun getUser(@Path("user") user: String): Call<GitHubUser>

}