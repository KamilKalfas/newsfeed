package io.peanutapp.newsfeed.core.application

import android.content.Context
import android.content.Intent

interface IntentFactory {

    fun <T> create(clazz: Class<T>): Intent

    class Impl(
        private val context: Context
    ) : IntentFactory {
        override fun <T> create(clazz: Class<T>) = Intent(context, clazz)
    }
}