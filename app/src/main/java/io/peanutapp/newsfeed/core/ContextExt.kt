package io.peanutapp.newsfeed.core

import android.content.Context
import android.net.ConnectivityManager

fun Context.isNetworkConnected(): Boolean {
    val connectivityManager = applicationContext.getSystemService(ConnectivityManager::class.java)
    return connectivityManager?.let {
        connectivityManager.activeNetworkInfo != null
    } ?: run {
        false
    }
}