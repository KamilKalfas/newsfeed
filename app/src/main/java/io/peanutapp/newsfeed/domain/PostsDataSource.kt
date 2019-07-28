package io.peanutapp.newsfeed.domain

import io.peanutapp.newsfeed.domain.postslist.entity.Post

interface PostsDataSource {
    suspend fun get(cursor: String) : List<Post>
}