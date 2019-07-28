package io.peanutapp.newsfeed.data.network

import io.peanutapp.newsfeed.data.postslist.entity.FeedResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApi {

    @GET("/posts")
    suspend fun getPosts(@Query(value = "after") cursor: String = ""): FeedResponse
}