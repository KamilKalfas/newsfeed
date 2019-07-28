package io.peanutapp.newsfeed.domain.network

import java.util.concurrent.TimeUnit

interface NetworkServiceConfig {
    val host: String
    val timeouts: Timeouts
    val authPolicy: AuthPolicy<Any>

    interface Timeouts {
        val connectTimeout : Pair<Long, TimeUnit>
        val writeTimeout : Pair<Long, TimeUnit>
        val readTimeout : Pair<Long, TimeUnit>
    }
}