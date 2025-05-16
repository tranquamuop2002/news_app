package com.example.news_app_mvvm.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app_mvvm.data.repository.NewsRepositoryImpl
import com.example.news_app_mvvm.domain.repository.NewsRepository
import com.example.news_app_mvvm.domain.usecase.GetNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getNewsUseCase: GetNewsUseCase) : ViewModel() {

    fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            getNewsUseCase.execute()
        }
    }

}