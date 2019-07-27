package io.peanutapp.newsfeed.data.network

import io.peanutapp.newsfeed.core.CredentialsPrefs
import io.peanutapp.newsfeed.domain.network.CredentialsProvider

class CredentialsStore(
    credentialsPrefs: CredentialsPrefs
) : CredentialsProvider.Store {
    override val user = credentialsPrefs.getUser()
    override val password = credentialsPrefs.getPassword()
}