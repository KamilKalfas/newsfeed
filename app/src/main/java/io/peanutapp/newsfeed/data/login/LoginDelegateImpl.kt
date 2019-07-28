package io.peanutapp.newsfeed.data.login

import io.peanutapp.newsfeed.core.CredentialsPrefs
import io.peanutapp.newsfeed.domain.login.LoginDelegate

class LoginDelegateImpl constructor(
    private val credentialsPrefs: CredentialsPrefs
) : LoginDelegate {

    override fun login(username: String, password: String) {
        credentialsPrefs.saveUser(username)
        credentialsPrefs.savePassword(password)
    }
}