package io.peanutapp.newsfeed.data.postslist

import io.peanutapp.newsfeed.data.postslist.entity.toPost
import io.peanutapp.newsfeed.domain.PostsDataSource
import io.peanutapp.newsfeed.domain.network.PostsService
import io.peanutapp.newsfeed.domain.postslist.entity.PostsRepositoryResult

class CloudDataSource(
    private val postsService: PostsService
) : PostsDataSource {

    override suspend fun get(cursor: String): PostsRepositoryResult {
        val result = postsService.getPosts(cursor)
        return PostsRepositoryResult(
            result.posts.asSequence().map { it.toPost() }.toList(),
            paginationCursor = result.paging.next_cursor)
    }
}