package io.peanutapp.newsfeed.data.postslist

import io.peanutapp.newsfeed.domain.PostsDataSource
import io.peanutapp.newsfeed.domain.PostsDataSourceFactory
import io.peanutapp.newsfeed.domain.network.PostsService

class PostsDataSourceFactoryImpl (
    private val postsService: PostsService
): PostsDataSourceFactory {

    override fun create(): PostsDataSource {
        return CloudDataSource(postsService)
    }
}