package io.peanutapp.newsfeed.core.network

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.peanutapp.newsfeed.core.ApplicationScope
import io.peanutapp.newsfeed.core.isNetworkConnected
import io.peanutapp.newsfeed.data.network.PostsServiceConfig
import io.peanutapp.newsfeed.data.network.PostsServiceImpl
import io.peanutapp.newsfeed.domain.network.*
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.internal.cache.CacheInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    @JvmStatic
    @Provides
    fun provideGson() = Gson()

    @Provides
    @JvmStatic
    fun provideCache(cacheDir: File) = Cache(cacheDir, 10 * 1024 * 1024)

    @Provides
    @JvmStatic
    fun provideCacheInterceptor(@ApplicationScope context: Context): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            request = if (context.isNetworkConnected()) {
                request.newBuilder()
                    .header("Cache-Control", "public, max-age=" + 5)
                    .build()
            } else {
                request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + TimeUnit.DAYS.toSeconds(1))
                    .build()
            }
            chain.proceed(request)
        }
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideNetworkServiceConfig(): NetworkServiceConfig = PostsServiceConfig()

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
        config: NetworkServiceConfig,
        cacheInterceptor: Interceptor
    ) = HttpClientFactory(credentialsProvider, credentialsStore, config, cacheInterceptor)

    @JvmStatic
    @Provides
    fun provideNetworkClientFactory(
        gson: Gson,
        config: NetworkServiceConfig
    ): NetworkClientFactory {
        return NetworkClientFactory(gson, config)
    }
}