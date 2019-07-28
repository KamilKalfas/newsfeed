package io.peanutapp.newsfeed.domain.network

import com.google.gson.Gson
import io.peanutapp.newsfeed.data.postslist.network.PostsApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NetworkClientFactory(
    private val gson: Gson,
    private val config: NetworkServiceConfig
) {
    fun create(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(config.host)
            .client(okHttpClient)
            .build()
    }
}

class HttpClientFactory(
    private val config: NetworkServiceConfig
) {
    fun create(): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                readTimeout(config.timeouts.readTimeout.first, config.timeouts.readTimeout.second)
                connectTimeout(config.timeouts.connectTimeout.first, config.timeouts.connectTimeout.second)
                writeTimeout(config.timeouts.writeTimeout.first, config.timeouts.writeTimeout.second)
                if (config.authPolicy.policy is Interceptor) {
                    addInterceptor(config.authPolicy.policy as Interceptor)
                }
            }.build()
    }
}

class PostsServiceFactory(
    private val networkClientFactory: NetworkClientFactory,
    private val httpClientFactory: HttpClientFactory
) {
    fun create(): PostsApi = networkClientFactory.create(httpClientFactory.create()).create()
}