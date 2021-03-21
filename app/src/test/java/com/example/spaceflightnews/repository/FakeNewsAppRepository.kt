package com.example.spaceflightnews.repository

import com.example.spaceflightnews.model.Article
import com.example.spaceflightnews.network.NetworkArticleEntity
import com.example.spaceflightnews.network.NetworkMapper
import com.example.spaceflightnews.util.Resource
import com.example.spaceflightnews.network.ResponseHandler
import java.lang.Exception

/**
 * a fake repository to simulate the network call response in the unit test
 */
class FakeNewsAppRepository : NewsAppRepository {
    private var shouldReturnNetworkError = false
    private var networkMapper = NetworkMapper()
    private var responseHandler = ResponseHandler()
    val networkArticle = NetworkArticleEntity(
        "id",
        "imageUrl",
        "Spaceflight Now",
        "2021-03-15T10:25:18.000Z",
        "Three Chinese military satellites launched Saturday aboard ",
        "China launches spy satellite triplet for third time this year",
        "https://spaceflightnow.com/2021/03/14/china-launches-spy-satellite-triplet-for-third-time-this-year/",
    )


    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getNews(): Resource<List<Article>> {

        return if (shouldReturnNetworkError) {
            responseHandler.handleException(Exception())
        } else {
            val articles = networkMapper.mapFromNetworkArticleList(listOf(networkArticle))
            responseHandler.handleSuccess(articles)
        }
    }


}


