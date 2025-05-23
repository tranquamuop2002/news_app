package com.example.news_app_mvvm.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app_mvvm.domain.model.News
import com.example.news_app_mvvm.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getNewsUseCase: GetNewsUseCase) : ViewModel() {

    private val _newLiveData = MutableLiveData<Boolean>()
    val newLiveData: MutableLiveData<Boolean> get() = _newLiveData
    val newsList = mutableListOf<News>()

    fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getNewsUseCase.getNews()
            newsList.clear()
            newsList.addAll(response)
            _newLiveData.postValue(true)
        }
    }

}