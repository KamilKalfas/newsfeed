package io.peanutapp.newsfeed.domain.network

interface CredentialsProvider<out T> {
    fun getCredentials(store: Store) : T

    interface Store {
        val user : String
        val password: String
    }
}