package com.example.newsapplication.modal

sealed class NewsUIState<out T> {

    object Loading: NewsUIState<Nothing>()
    data class Success<T>(val data:T): NewsUIState<T>()
    data class Error<Nothing>(val message:String): NewsUIState<Nothing>()
}