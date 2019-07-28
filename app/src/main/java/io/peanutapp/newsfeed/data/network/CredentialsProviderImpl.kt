package io.peanutapp.newsfeed.data.network

import io.peanutapp.newsfeed.domain.network.CredentialsProvider
import okhttp3.Credentials

class CredentialsProviderImpl : CredentialsProvider<String> {

    override fun getCredentials(store: CredentialsProvider.Store): String {
        return Credentials.basic(store.user, store.password)
    }
}