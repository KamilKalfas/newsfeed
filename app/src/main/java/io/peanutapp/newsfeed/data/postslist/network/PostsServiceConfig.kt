package io.peanutapp.newsfeed.data.postslist.network

import io.peanutapp.newsfeed.domain.network.AuthPolicy
import io.peanutapp.newsfeed.domain.network.NetworkServiceConfig
import io.peanutapp.newsfeed.domain.network.NetworkServiceConfig.Timeouts
import okhttp3.Interceptor
import java.util.concurrent.TimeUnit

class PostsServiceConfig(
    override val authPolicy: AuthPolicy<Interceptor>
) : NetworkServiceConfig {
    override val host = "http://exercice.production.backend.teampeanut.com"

    override val timeouts = object : Timeouts {
        override val connectTimeout = 10 to TimeUnit.SECONDS
        override val writeTimeout = 10 to TimeUnit.SECONDS
        override val readTimeout = 10 to TimeUnit.SECONDS
    }
}