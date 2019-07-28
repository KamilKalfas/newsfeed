package io.peanutapp.newsfeed.domain.network

import com.google.gson.Gson
import io.peanutapp.newsfeed.data.network.PostsApi
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