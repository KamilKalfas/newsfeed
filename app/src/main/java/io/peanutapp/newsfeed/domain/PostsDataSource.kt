package io.peanutapp.newsfeed.domain

import io.peanutapp.newsfeed.domain.entity.Post

interface PostsDataSource {
    fun get(cursor: String) : List<Post>
}