package io.peanutapp.newsfeed.domain.postslist.entity

data class PostsRepositoryResult(
    val posts: List<Post>,
    val paginationCursor: String = ""
)