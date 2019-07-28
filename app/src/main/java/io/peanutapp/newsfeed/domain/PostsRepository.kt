package io.peanutapp.newsfeed.domain

import io.peanutapp.newsfeed.domain.postslist.entity.Post

interface PostsRepository {
    suspend fun getPosts(cursor: String): List<Post>
}