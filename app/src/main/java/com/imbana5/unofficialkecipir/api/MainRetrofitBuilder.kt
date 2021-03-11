package com.imbana5.unofficialkecipir.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MainRetrofitBuilder {
    const val BASE_URL: String = "https://cdn.kecipir.com/"

    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiService: ApiService by lazy {
        retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }

}