package com.example.spaceflightnews.repository

import com.example.spaceflightnews.model.Article
import com.example.spaceflightnews.util.Resource

/**
 * I created this interface to make my code more testable
 * so i can use this repository while I do the unit test
 * we can unit test the app with out making an unnecessary network request
 */
interface NewsAppRepository {
    suspend fun getNews(): Resource<List<Article>>
}