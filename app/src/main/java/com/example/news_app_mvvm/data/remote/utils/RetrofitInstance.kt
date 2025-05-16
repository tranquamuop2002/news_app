package com.example.news_app_mvvm.data.remote.utils

import com.example.news_app_mvvm.data.remote.service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val apiInterface: ApiService by lazy {
        Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }
}