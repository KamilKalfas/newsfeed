package io.peanutapp.newsfeed.domain

import io.peanutapp.newsfeed.core.DispatcherProvider
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

interface Interactor<in Params, out Results> {
    val dispatchers: DispatcherProvider
    suspend operator fun invoke(params: Params): Results
}

fun CoroutineScope.work(interactor: Interactor<Unit, Unit>) = work(interactor, Unit)

fun <Params> CoroutineScope.work(interactor: Interactor<Params, Unit>, params: Params): Job {
    return launch(context = interactor.dispatchers.io(), block = { interactor(params) })
}

fun <Results> CoroutineScope.workAsync(interactor: Interactor<Unit, Results>) = workAsync(interactor, Unit)

fun <Params, Results> CoroutineScope.workAsync(
    interactor: Interactor<Params, Results>,
    params: Params
): Deferred<Results> {
    return async(context = interactor.dispatchers.io()) { interactor(params) }
}

infix fun <T> Deferred<T>.then(uiFunction: (T) -> Unit) {
     class UiScope : CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = Job() + Dispatchers.Main
    }
    UiScope().launch {
        uiFunction(this@then.await())
    }
}