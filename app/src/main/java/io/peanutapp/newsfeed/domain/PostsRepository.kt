package io.peanutapp.newsfeed.domain

import io.peanutapp.newsfeed.domain.entity.Post

interface PostsRepository {
    fun getPosts(cursor: String): List<Post>
}