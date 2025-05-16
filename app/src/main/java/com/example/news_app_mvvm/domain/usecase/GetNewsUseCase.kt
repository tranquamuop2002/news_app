package com.example.news_app_mvvm.domain.usecase

import com.example.news_app_mvvm.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend fun execute() {
        newsRepository.getPost()
    }
}