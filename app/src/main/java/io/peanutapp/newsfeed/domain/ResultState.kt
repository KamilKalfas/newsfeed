package io.peanutapp.newsfeed.domain

import java.lang.Exception

sealed class ResultState {
    data class Success<out T: Any>(val data: T) : ResultState()
    data class Failure(val error: Throwable) : ResultState()
}

inline fun <reified T : Any> safeWrap(block: () -> T) : ResultState {
    return try {
        ResultState.Success(block.invoke())
    } catch (e : Exception) {
        ResultState.Failure(e)
    }
}