package io.peanutapp.newsfeed.domain

import kotlinx.coroutines.*

interface Interactor<in Params, out Results> {
    val dispatcher: CoroutineDispatcher
    suspend operator fun invoke(params: Params): Results
}

fun CoroutineScope.work(interactor: Interactor<Unit, Unit>) = work(interactor, Unit)

fun <Params> CoroutineScope.work(interactor: Interactor<Params, Unit>, params: Params): Job {
    return launch(context = interactor.dispatcher, block = { interactor(params) })
}

fun <Results> CoroutineScope.workAsync(interactor: Interactor<Unit, Results>) = workAsync(interactor, Unit)

fun <Params, Results> CoroutineScope.workAsync(
    interactor: Interactor<Params, Results>,
    params: Params
): Deferred<Results> {
    return async(context = interactor.dispatcher) { interactor(params) }
}