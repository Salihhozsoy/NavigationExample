package com.example.navigationfirstproject.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationfirstproject.AppDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SecondFragmentViewModel:ViewModel() {

    private val _newsListState: MutableStateFlow<NewsListState> = MutableStateFlow(NewsListState.Idle)
    val newsListState: StateFlow<NewsListState> = _newsListState

    fun getNews(appDatabase: AppDatabase){
        viewModelScope.launch {
            runCatching {
                _newsListState.value = NewsListState.Loading
                val news = appDatabase.newsDao().getNews()
                _newsListState.value = if (news.isEmpty()) NewsListState.Empty else NewsListState.Result(news.toMutableList()
                )
            }.onFailure {
                _newsListState.value = NewsListState.Error(it)
            }
        }
    }
}