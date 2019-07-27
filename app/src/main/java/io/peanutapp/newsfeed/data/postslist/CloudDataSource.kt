package io.peanutapp.newsfeed.data.postslist

import io.peanutapp.newsfeed.data.postslist.entity.toPost
import io.peanutapp.newsfeed.domain.PostsDataSource
import io.peanutapp.newsfeed.domain.postslist.entity.Post
import io.peanutapp.newsfeed.domain.network.PostsService

class CloudDataSource(
    private val postsService: PostsService
) : PostsDataSource {

    override fun get(cursor: String): List<Post> {
        return postsService.getPosts(cursor).posts.map { it.toPost() }
    }
}