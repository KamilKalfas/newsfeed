package io.peanutapp.newsfeed.core

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    fun provideMainDispatcher() : CoroutineDispatcher
    fun provideIoDispatcher() : CoroutineDispatcher
}