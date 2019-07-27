package io.peanutapp.newsfeed.core

import android.content.Context

private const val FILE_KEY = "CREDENTIALS_PREFS"
private const val USERNAME_KEY = "key_usr"
private const val PASSWORD_KEY = "key_pwd"
private const val EMPTY = ""

interface CredentialsPrefs {

    fun saveUser(user: String)
    fun savePassword(password: String)
    fun getUser() : String
    fun getPassword() : String

    class Impl(
        context: Context
    ) : CredentialsPrefs {

        private val credentialsPrefs by lazy {
            context.getSharedPreferences(FILE_KEY, Context.MODE_PRIVATE)
        }

        override fun saveUser(user: String) = credentialsPrefs.edit().putString(USERNAME_KEY, user).apply()
        override fun savePassword(password: String) = credentialsPrefs.edit().putString(PASSWORD_KEY, password).apply()

        override fun getUser() = credentialsPrefs.getString(USERNAME_KEY, EMPTY)!!
        override fun getPassword() = credentialsPrefs.getString(PASSWORD_KEY, EMPTY)!!
    }
}