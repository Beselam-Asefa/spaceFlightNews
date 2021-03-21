package com.example.spaceflightnews.network

import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  an interface for defining the api request endpoints
 */
interface NewsApi {
    @GET("v2/articles")
    suspend fun getNews(@Query("_limit") limit:Int=7): List<NetworkArticleEntity>
}