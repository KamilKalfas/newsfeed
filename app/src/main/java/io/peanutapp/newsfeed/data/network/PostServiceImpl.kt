package io.peanutapp.newsfeed.data.network

import io.peanutapp.newsfeed.data.entity.FeedResponse
import io.peanutapp.newsfeed.data.network.PostsApi
import io.peanutapp.newsfeed.domain.network.PostsService

class PostsServiceImpl(
    private val postsApi: PostsApi
) : PostsService {

    override fun getPosts(cursor: String): FeedResponse {
        return postsApi.getPosts(cursor)
    }
}