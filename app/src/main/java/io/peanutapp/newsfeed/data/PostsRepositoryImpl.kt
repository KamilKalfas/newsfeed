package io.peanutapp.newsfeed.data

import io.peanutapp.newsfeed.domain.PostsDataSourceFactory
import io.peanutapp.newsfeed.domain.PostsRepository

class PostsRepositoryImpl(
    postsDataSourceFactory: PostsDataSourceFactory
) : PostsRepository {

    private val dataSource by lazy {
        postsDataSourceFactory.create()
    }

    override fun getPosts(cursor: String) = dataSource.get(cursor)
}