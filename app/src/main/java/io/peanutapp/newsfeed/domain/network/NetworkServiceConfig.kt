package io.peanutapp.newsfeed.domain.network

import java.util.concurrent.TimeUnit

interface NetworkServiceConfig {
    val host: String
    val timeouts: Timeouts
    val authPolicy: AuthPolicy<Any>

    interface Timeouts {
        val connectTimeout : Pair<Int, TimeUnit>
        val writeTimeout : Pair<Int, TimeUnit>
        val readTimeout : Pair<Int, TimeUnit>
    }
}