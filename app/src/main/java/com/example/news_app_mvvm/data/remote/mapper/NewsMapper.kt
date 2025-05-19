package com.example.news_app_mvvm.data.remote.mapper

import com.example.news_app_mvvm.data.remote.response.NewsResponse
import com.example.news_app_mvvm.domain.model.News

fun List<NewsResponse>.toResponse(): List<News> {
    return map {
        News(
            userId = it.userId,
            id = it.id,
            title = it.title,
            body = it.body
        )
    }
}