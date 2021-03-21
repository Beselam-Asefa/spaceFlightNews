package com.example.spaceflightnews.repository

import com.example.spaceflightnews.network.NewsApi
import com.example.spaceflightnews.model.Article
import com.example.spaceflightnews.network.NetworkMapper
import com.example.spaceflightnews.network.ResponseHandler
import com.example.spaceflightnews.util.Resource
import javax.inject.Inject

/**
 * the default repository I used  for this app
 * it inherit the newsAppRepository
 */
class DefaultNewsAppRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val networkMapper: NetworkMapper,
    private val responseHandler: ResponseHandler
) : NewsAppRepository {


    override suspend fun getNews(): Resource<List<Article>> {
        return try {
            val response = newsApi.getNews()
            val articles = networkMapper.mapFromNetworkArticleList(response)
            responseHandler.handleSuccess(articles)
        } catch (e: Exception) {
            return responseHandler.handleException(e)
        }


    }
}