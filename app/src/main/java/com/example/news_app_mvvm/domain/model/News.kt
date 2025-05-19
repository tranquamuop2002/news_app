package com.example.news_app_mvvm.domain.model

data class News(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
)