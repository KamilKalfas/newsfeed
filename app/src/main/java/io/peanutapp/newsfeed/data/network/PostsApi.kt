package io.peanutapp.newsfeed.data.network

import io.peanutapp.newsfeed.data.entity.FeedResponse

interface PostsApi {
    fun getPosts(cursor: String) : FeedResponse
}

