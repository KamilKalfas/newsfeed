package io.peanutapp.newsfeed.domain

import io.peanutapp.newsfeed.data.entity.toPost
import io.peanutapp.newsfeed.domain.entity.Post
import io.peanutapp.newsfeed.domain.network.PostsService

class CloudDataSource(
    private val postsService: PostsService
) : PostsDataSource {

    override fun get(cursor: String): List<Post> {
        return postsService.getPosts(cursor).posts.map { it.toPost() }
    }
}