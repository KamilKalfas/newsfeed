package io.peanutapp.newsfeed.domain.network

import io.peanutapp.newsfeed.data.entity.FeedResponse

interface PostsService {
    fun getPosts(cursor: String) : FeedResponse
}