package com.example.news_app_mvvm.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.news_app_mvvm.data.remote.base.ApiResponse
import com.example.news_app_mvvm.data.remote.response.NewsResponse
import com.example.news_app_mvvm.data.remote.service.ApiService
import com.example.news_app_mvvm.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiInterface: ApiService,
) : NewsRepository {

    private val _responseLiveData = MutableLiveData<ApiResponse<NewsResponse>>()
    val responseLiveData: MutableLiveData<ApiResponse<NewsResponse>>
        get() = _responseLiveData

    override suspend fun getPost() {
        _responseLiveData.postValue(ApiResponse.ApiLoading())
        try {
            val response = apiInterface.getPosts()
            if (response.body() != null) {
                _responseLiveData.postValue(ApiResponse.ApiSuccess(response.body()))
            } else {
                throw Exception("Something went wrong")
            }
        } catch (e: Exception) {
            _responseLiveData.postValue(ApiResponse.ApiError(e.localizedMessage))
        }
    }
}