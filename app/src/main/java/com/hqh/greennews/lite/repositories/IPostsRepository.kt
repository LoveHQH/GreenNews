package com.hqh.greennews.lite.repositories

import com.hqh.greennews.lite.model.Poster
import com.hqh.greennews.viewmodels.PostsFeed

interface IPostsRepository {
    /**
     * Get Posts.
     */
    suspend fun getPostsFeed(): Result<PostsFeed>
}