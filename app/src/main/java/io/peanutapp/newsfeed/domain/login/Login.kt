package io.peanutapp.newsfeed.domain.login

import io.peanutapp.newsfeed.core.DispatcherProvider
import io.peanutapp.newsfeed.domain.Interactor
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class Login @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val loginDelegate: LoginDelegate
) : Interactor<Login.Params, Unit> {

    override val dispatcher: CoroutineDispatcher
        get() = dispatcherProvider.provideIoDispatcher()

    override suspend fun invoke(params: Params) {
        loginDelegate.login(params.username, params.password)
    }

    data class Params(
        val username: String,
        val password: String
    )
}