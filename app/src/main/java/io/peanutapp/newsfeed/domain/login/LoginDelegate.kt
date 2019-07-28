package io.peanutapp.newsfeed.domain.login

interface LoginDelegate {
    fun login(username: String, password: String)
}