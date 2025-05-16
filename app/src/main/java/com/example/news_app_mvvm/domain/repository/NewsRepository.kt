package com.example.news_app_mvvm.domain.repository

interface NewsRepository {
    suspend fun getPost()
}