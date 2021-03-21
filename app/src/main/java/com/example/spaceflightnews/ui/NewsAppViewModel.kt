package com.example.spaceflightnews.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceflightnews.model.Article
import com.example.spaceflightnews.repository.NewsAppRepository
import com.example.spaceflightnews.util.Resource
import kotlinx.coroutines.launch

/**
 * the view model class
 * I used constructor injection to access the default repository
 * I used two  article LiveData's , one is private  the other one is not
 * and I used it to observe the Api response in the main activity
 */

class NewsAppViewModel @ViewModelInject constructor(private val newsAppRepository: NewsAppRepository) :
    ViewModel() {

    private val _articles = MutableLiveData<Resource<List<Article>>>()
    val articles: LiveData<Resource<List<Article>>> = _articles


    fun getNews() = viewModelScope.launch {
        _articles.value = Resource.Loading()

        when (val response = newsAppRepository.getNews()) {
            is Resource.Error -> {
                _articles.value = response.message?.let { Resource.Error(it) }
            }
            is Resource.Success -> {
                val result = response.data
                if (result == null) {
                    _articles.value = Resource.Error("Something went wrong")
                } else {
                    _articles.value = Resource.Success(result)
                }
            }
        }
    }


}