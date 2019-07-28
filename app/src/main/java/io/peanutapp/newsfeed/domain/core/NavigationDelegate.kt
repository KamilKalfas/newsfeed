package io.peanutapp.newsfeed.domain.core

import android.content.Intent
import io.peanutapp.newsfeed.core.application.IntentFactory
import io.peanutapp.newsfeed.domain.login.SessionDelegate
import io.peanutapp.newsfeed.presentation.login.LoginActivity
import io.peanutapp.newsfeed.presentation.postslist.PostsListActivity

interface NavigationDelegate {

    fun getActivityIntent(): Intent

    class Impl(
        private val sessionDelegate: SessionDelegate,
        private val intentFactory: IntentFactory
    ) : NavigationDelegate {
        override fun getActivityIntent() = if (sessionDelegate.isUserLoggedIn()) {
            intentFactory.create(PostsListActivity::class.java)
        } else {
            intentFactory.create(LoginActivity::class.java)
        }
    }
}