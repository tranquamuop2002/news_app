package com.example.news_app_mvvm.data.remote.response

data class NewsResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
)