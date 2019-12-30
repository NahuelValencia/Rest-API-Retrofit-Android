package com.example.rest_api_retrofit_android.Rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    val BASE_URL = "https://api.github.com/"
    var retrofit: Retrofit? = null

    fun getCient(): Retrofit {

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit!!
    }

}