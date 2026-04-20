package com.example.newsapplication.network

import com.example.newsapplication.modal.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPi {


    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("category") category: String,
    ): NewsResponse



    @GET("v2/everything")
    suspend fun getNewsBySearch(
        @Query("q") search: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}