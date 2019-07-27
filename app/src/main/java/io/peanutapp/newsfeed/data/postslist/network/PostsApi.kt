package io.peanutapp.newsfeed.data.postslist.network

import io.peanutapp.newsfeed.data.postslist.entity.FeedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApi {

    @GET("/posts")
    fun getPosts(@Query(value = "after") cursor: String = ""): FeedResponse
}