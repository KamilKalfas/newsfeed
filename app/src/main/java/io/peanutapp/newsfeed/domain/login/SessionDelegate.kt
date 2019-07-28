package io.peanutapp.newsfeed.domain.login

interface SessionDelegate {
    fun isUserLoggedIn() : Boolean
}