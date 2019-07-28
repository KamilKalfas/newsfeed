package io.peanutapp.newsfeed.data.login

import io.peanutapp.newsfeed.core.CredentialsPrefs
import io.peanutapp.newsfeed.domain.login.SessionDelegate

class SessionDelegateImpl(
    private val credentialsPrefs: CredentialsPrefs
) : SessionDelegate {

    override fun isUserLoggedIn() = credentialsPrefs.getUser().isNotBlank() and credentialsPrefs.getPassword().isNotBlank()
}