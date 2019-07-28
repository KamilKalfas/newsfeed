package io.peanutapp.newsfeed.domain.network

import io.peanutapp.newsfeed.data.postslist.entity.FeedResponse

interface PostsService {
    suspend fun getPosts(cursor: String) : FeedResponse
}