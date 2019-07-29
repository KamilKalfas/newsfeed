package io.peanutapp.newsfeed.presentation.postslist.recycler

import javax.inject.Provider

class PostItemViewFactory constructor(
    private val provider: Provider<PostItemView>
) {
    fun create(params: Params): PostItemView {
        return provider.get().apply {
            title.set(params.title)
            author.set(params.author)
            body.set(params.body)
        }
    }

    data class Params(
        val title: String,
        val author: String,
        val body: String
    )
}