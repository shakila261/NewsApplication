package com.example.newsapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.modal.Constant
import com.example.newsapplication.modal.NewsResponse
import com.example.newsapplication.modal.NewsUIState
import com.example.newsapplication.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val repo = NewsRepository()

    private val _newsState =
        MutableStateFlow<NewsUIState<NewsResponse>>(NewsUIState.Loading)

    val news: StateFlow<NewsUIState<NewsResponse>> = _newsState

    fun getNews(country: String, category: String = "GENERAL") {

        viewModelScope.launch {
            _newsState.value = NewsUIState.Loading

            try {
                val result = repo.getNews(country, Constant.apiKey, category)
                _newsState.value = NewsUIState.Success(result)



            } catch (e: Exception) {
                e.printStackTrace()

                _newsState.value =
                    NewsUIState.Error(e.message ?: "Error")

            }
        }
    }


    fun getNewsBySearch(search: String) {
        viewModelScope.launch {
            _newsState.value = NewsUIState.Loading
            try {
                val result = repo.getNewsBySearch(search, Constant.apiKey)
                _newsState.value = NewsUIState.Success(result)
            } catch (e: Exception) {
                _newsState.value =
                    NewsUIState.Error(e.message ?: "Error")

            }
        }
    }


}