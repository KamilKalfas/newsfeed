package io.peanutapp.newsfeed.data.network

import io.peanutapp.newsfeed.domain.network.NetworkServiceConfig
import io.peanutapp.newsfeed.domain.network.NetworkServiceConfig.Timeouts
import java.util.concurrent.TimeUnit

class PostsServiceConfig : NetworkServiceConfig {
    override val host = "http://exercice.production.backend.teampeanut.com"

    override val timeouts = object : Timeouts {
        override val connectTimeout = Timeouts.Timeout(10L, TimeUnit.SECONDS)
        override val writeTimeout = Timeouts.Timeout(10L, TimeUnit.SECONDS)
        override val readTimeout = Timeouts.Timeout(10L, TimeUnit.SECONDS)
    }
}