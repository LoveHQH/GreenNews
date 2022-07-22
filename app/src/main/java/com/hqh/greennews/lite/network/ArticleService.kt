package com.hqh.greennews.lite.network

import com.hqh.greennews.lite.model.Poster
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface ArticleService {

    @GET("topic_lastest.json")
    suspend fun getArticleLastest(): ApiResponse<List<Poster>>

    @GET("topic.json")
    suspend fun getArticle(): ApiResponse<List<Poster>>

    @GET("topicByID.json")
    suspend fun getTopicByID(): ApiResponse<Poster>

    @GET("topicBySearch.json")
    suspend fun getTopicBySearch(): ApiResponse<List<Poster>>

    @GET("topicByTag.json")
    suspend fun getTopicByTag(): ApiResponse<List<Poster>>


}
