package com.example.newsapplication.repository

import com.example.newsapplication.modal.NewsResponse
import com.example.newsapplication.network.RetrofitInstance

class NewsRepository {

    suspend fun getNews(country: String, apiKey: String, category: String): NewsResponse {
        return RetrofitInstance.api.getNews(country, apiKey, category)

    }

    suspend fun getNewsBySearch(search: String, apiKey: String): NewsResponse {

        return RetrofitInstance.api.getNewsBySearch(search, apiKey)

    }
}