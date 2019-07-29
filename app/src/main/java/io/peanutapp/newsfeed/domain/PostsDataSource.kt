package io.peanutapp.newsfeed.domain

import io.peanutapp.newsfeed.domain.postslist.entity.PostsRepositoryResult

interface PostsDataSource {
    suspend fun get(cursor: String) : PostsRepositoryResult
}