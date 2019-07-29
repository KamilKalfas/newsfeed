package io.peanutapp.newsfeed.core

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    fun main() : CoroutineDispatcher
    fun io() : CoroutineDispatcher
}