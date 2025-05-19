package com.example.news_app_mvvm.data.repository

import android.util.Log
import com.example.news_app_mvvm.data.remote.mapper.toResponse
import com.example.news_app_mvvm.data.remote.service.ApiService
import com.example.news_app_mvvm.domain.model.News
import com.example.news_app_mvvm.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val apiService: ApiService) : NewsRepository {
    override suspend fun getNews(): List<News> {
        val response = apiService.getPosts()

        if (response.isSuccessful && response.body() != null) {
            val data = response.body()!!
            Log.d("VietTD", "Response data: $data")
            return data.toResponse()
        } else {
            Log.e("VietTD", "API failed: ${response.errorBody()?.string()}")
            return emptyList()
        }
    }
}
