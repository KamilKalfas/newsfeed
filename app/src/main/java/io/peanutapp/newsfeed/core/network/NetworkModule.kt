package io.peanutapp.newsfeed.core.network

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.peanutapp.newsfeed.domain.network.HttpClientFactory
import io.peanutapp.newsfeed.domain.network.NetworkClientFactory
import io.peanutapp.newsfeed.domain.network.NetworkServiceConfig
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @JvmStatic
    fun provideGson() = Gson()

    @Provides
    @JvmStatic
    @Singleton
    fun provideNetworkClientFactory(
        gson: Gson,
        config: NetworkServiceConfig
    ): NetworkClientFactory {
        return NetworkClientFactory(gson, config)
    }

    @Provides
    @JvmStatic
    @Singleton
    fun provideHttpClientFactory(
        config: NetworkServiceConfig
    ): HttpClientFactory {
        return HttpClientFactory(config)
    }
}