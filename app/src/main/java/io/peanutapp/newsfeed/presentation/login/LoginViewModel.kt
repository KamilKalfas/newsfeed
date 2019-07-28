package io.peanutapp.newsfeed.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.peanutapp.newsfeed.core.ViewContract
import io.peanutapp.newsfeed.core.application.IntentFactory
import io.peanutapp.newsfeed.domain.login.Login
import io.peanutapp.newsfeed.domain.work
import io.peanutapp.newsfeed.presentation.postslist.PostsListActivity
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val login: Login,
    private val loginView: LoginView,
    private val intentFactory: IntentFactory
) : ViewModel(), ViewContract.Callback {

    val view get() = loginView

    override fun onViewBind() {
        loginView.changeState(LoginView.State.Init(
            loginAction = { user, pwd -> login(user, pwd)}
        ))
    }

    private fun login(username: String, password: String) {
        viewModelScope.work(login, Login.Params(username, password))
        loginView.changeState(LoginView.State.Success(intentFactory.create(PostsListActivity::class.java)))
    }

    override fun onCleared() {
        super.onCleared()
        loginView.unbind()
    }
}