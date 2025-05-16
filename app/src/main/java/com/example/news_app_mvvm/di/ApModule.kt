package com.example.news_app_mvvm.di

import com.example.news_app_mvvm.data.remote.service.ApiService
import com.example.news_app_mvvm.data.repository.NewsRepositoryImpl
import com.example.news_app_mvvm.domain.repository.NewsRepository
import com.example.news_app_mvvm.domain.usecase.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.example.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // API
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    // Repository
    @Provides
    @Singleton
    fun provideNewsRepository(api: ApiService): NewsRepository {
        return NewsRepositoryImpl(api)
    }

    // UseCase
    @Provides
    @Singleton
    fun provideGetNewsUseCase(newsRepository: NewsRepository): GetNewsUseCase {
        return GetNewsUseCase(newsRepository)
    }
}
