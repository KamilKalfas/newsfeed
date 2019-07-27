package io.peanutapp.newsfeed.domain

interface Interactor<in Params, out Results> {
    fun run(params: Params): Results
}