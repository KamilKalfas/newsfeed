package io.peanutapp.newsfeed.domain.login

import io.peanutapp.newsfeed.core.DispatcherProvider
import io.peanutapp.newsfeed.domain.Interactor
import javax.inject.Inject

class Login @Inject constructor(
    private val loginDelegate: LoginDelegate,
    override val dispatchers: DispatcherProvider
) : Interactor<Login.Params, Unit> {

    override suspend fun invoke(params: Params) {
        loginDelegate.login(params.username, params.password)
    }

    data class Params(
        val username: String,
        val password: String
    )
}