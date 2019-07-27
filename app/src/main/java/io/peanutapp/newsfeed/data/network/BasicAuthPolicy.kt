package io.peanutapp.newsfeed.data.network

import io.peanutapp.newsfeed.domain.network.AUTHORIZATION_HEADER
import io.peanutapp.newsfeed.domain.network.AuthPolicy
import io.peanutapp.newsfeed.domain.network.CredentialsProvider
import okhttp3.Interceptor

class BasicAuthPolicy(
    private val credentialsProvider: CredentialsProvider<String>,
    private val credentialsStore: CredentialsStore
) : AuthPolicy<Interceptor> {

    override val policy = Interceptor {
        val request = it.request()
        val credentials = credentialsProvider.getCredentials(credentialsStore)
        val authenticatedRequest = request.newBuilder()
            .addHeader(AUTHORIZATION_HEADER, credentials)
            .build()
        it.proceed(authenticatedRequest)
    }
}