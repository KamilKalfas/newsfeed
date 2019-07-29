package io.peanutapp.newsfeed.domain

import io.peanutapp.newsfeed.domain.postslist.entity.PostsRepositoryResult

interface PostsRepository {
    suspend fun getPosts(cursor: String): PostsRepositoryResult
}