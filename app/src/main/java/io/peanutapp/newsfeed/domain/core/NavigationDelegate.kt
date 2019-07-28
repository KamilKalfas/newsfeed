package io.peanutapp.newsfeed.domain.core

import android.content.Context
import android.content.Intent
import io.peanutapp.newsfeed.domain.login.SessionDelegate
import io.peanutapp.newsfeed.presentation.login.LoginActivity
import io.peanutapp.newsfeed.presentation.postslist.PostsListActivity

interface NavigationDelegate {

    fun getActivityIntent(): Intent

    class Impl(
        private val sessionDelegate: SessionDelegate,
        private val context: Context
    ) : NavigationDelegate {
        override fun getActivityIntent() = if (sessionDelegate.isUserLoggedIn()) {
            Intent(context, PostsListActivity::class.java)
        } else {
            Intent(context, LoginActivity::class.java)
        }
    }
}