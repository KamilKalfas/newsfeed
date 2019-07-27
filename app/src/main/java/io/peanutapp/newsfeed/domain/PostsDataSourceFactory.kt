package io.peanutapp.newsfeed.domain

interface PostsDataSourceFactory {
    fun create() : PostsDataSource
}