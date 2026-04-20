package com.example.newsapplication.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object RetrofitInstance {

    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()


    val api: NewsAPi= Retrofit
        .Builder()
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsAPi::class.java)



}

