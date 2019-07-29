package io.peanutapp.newsfeed.domain.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient

const val AUTHORIZATION_HEADER = "Authorization"

class HttpClientFactory(
    private val credentialsProvider: CredentialsProvider<String>,
    private val credentialsStore: CredentialsProvider.Store,
    private val config: NetworkServiceConfig,
    private val cacheInterceptor: Interceptor
) {
    fun create(): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                readTimeout(config.timeouts.readTimeout.value, config.timeouts.readTimeout.timeUnit)
                connectTimeout(config.timeouts.connectTimeout.value, config.timeouts.connectTimeout.timeUnit)
                writeTimeout(config.timeouts.writeTimeout.value, config.timeouts.writeTimeout.timeUnit)
                addInterceptor {
                    val request = it.request()
                    val credentials = credentialsProvider.getCredentials(credentialsStore)
                    val authenticatedRequest = request.newBuilder()
                        .addHeader(AUTHORIZATION_HEADER, credentials)
                        .build()
                    it.proceed(authenticatedRequest)
                }
                addInterceptor(cacheInterceptor)
            }.build()
    }
}