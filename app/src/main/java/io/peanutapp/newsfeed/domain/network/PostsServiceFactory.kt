package io.peanutapp.newsfeed.domain.network

import io.peanutapp.newsfeed.data.network.PostsApi
import retrofit2.create

class PostsServiceFactory(
    private val networkClientFactory: NetworkClientFactory,
    private val httpClientFactory: HttpClientFactory
) {
    fun create(): PostsApi = networkClientFactory.create(httpClientFactory.create()).create()
}