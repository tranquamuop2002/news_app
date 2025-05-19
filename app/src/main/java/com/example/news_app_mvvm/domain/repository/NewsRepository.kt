package com.example.news_app_mvvm.domain.repository

import com.example.news_app_mvvm.domain.model.News

interface NewsRepository {
    suspend fun getNews() : List<News>
}