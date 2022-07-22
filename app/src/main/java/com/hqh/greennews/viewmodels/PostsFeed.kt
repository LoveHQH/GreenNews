package com.hqh.greennews.viewmodels

import com.hqh.greennews.lite.model.Poster

data class PostsFeed(
    val highlightedPost: Poster,
    val recommendedPosts: List<Poster>,
    val popularPosts: List<Poster>,
    val recentPosts: List<Poster>,
) {
    /**
     * Returns a flattened list of all posts contained in the feed.
     */
    val allPosts: List<Poster> =
        listOf(highlightedPost) + recommendedPosts + popularPosts + recentPosts
}