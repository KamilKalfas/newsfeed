package io.peanutapp.newsfeed.core.network

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.peanutapp.newsfeed.data.network.PostsServiceConfig
import io.peanutapp.newsfeed.data.network.PostsServiceImpl
import io.peanutapp.newsfeed.domain.network.*
import javax.inject.Singleton

@Module
object NetworkModule {

    @JvmStatic
    @Provides
    fun provideGson() = Gson()

    @JvmStatic
    @Provides
    @Singleton
    fun provideNetworkServiceConfig() : NetworkServiceConfig = PostsServiceConfig()

    @JvmStatic
    @Provides
    @Singleton
    fun providePostsService(postsServiceFactory: PostsServiceFactory): PostsService {
        return PostsServiceImpl(postsServiceFactory.create())
    }

    @JvmStatic
    @Provides
    @Singleton
    fun providePostsServiceFactory(
        httpClientFactory: HttpClientFactory,
        networkClientFactory: NetworkClientFactory
    ): PostsServiceFactory {
        return PostsServiceFactory(networkClientFactory, httpClientFactory)
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideHttpClientFactory(
        credentialsProvider: CredentialsProvider<String>,
        credentialsStore: CredentialsProvider.Store,
        config: NetworkServiceConfig
    ) = HttpClientFactory(credentialsProvider, credentialsStore, config)

    @JvmStatic
    @Provides
    fun provideNetworkClientFactory(
        gson: Gson,
        config: NetworkServiceConfig
    ): NetworkClientFactory {
        return NetworkClientFactory(gson, config)
    }
}