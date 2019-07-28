package io.peanutapp.newsfeed.data.network

import io.peanutapp.newsfeed.data.postslist.entity.FeedResponse
import io.peanutapp.newsfeed.domain.network.PostsService
import kotlinx.coroutines.Deferred

class PostsServiceImpl(
    private val postsApi: PostsApi
) : PostsService {

    override suspend fun getPosts(cursor: String): FeedResponse {
        return postsApi.getPosts(cursor)
    }
}