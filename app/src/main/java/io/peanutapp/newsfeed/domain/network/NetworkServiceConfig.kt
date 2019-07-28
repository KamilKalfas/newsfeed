package io.peanutapp.newsfeed.domain.network

import java.util.concurrent.TimeUnit

interface NetworkServiceConfig {
    val host: String
    val timeouts: Timeouts

    interface Timeouts {
        val connectTimeout : Timeout
        val writeTimeout : Timeout
        val readTimeout : Timeout

        data class Timeout(val value: Long, val timeUnit: TimeUnit)
    }
}