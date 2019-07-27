package io.peanutapp.newsfeed.domain.network

const val AUTHORIZATION_HEADER = "Authorization"

interface AuthPolicy<out T: Any> {
    val policy : T
}