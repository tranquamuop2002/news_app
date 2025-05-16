package com.example.news_app_mvvm.data.remote.service

import com.example.news_app_mvvm.data.remote.response.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/posts/1")
    suspend fun getPosts(): Response<NewsResponse>
}