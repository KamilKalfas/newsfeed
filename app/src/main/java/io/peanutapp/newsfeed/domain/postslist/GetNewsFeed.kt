package io.peanutapp.newsfeed.domain.postslist

import io.peanutapp.newsfeed.core.DispatcherProvider
import io.peanutapp.newsfeed.domain.Interactor
import io.peanutapp.newsfeed.domain.PostsRepository
import io.peanutapp.newsfeed.domain.postslist.entity.Post
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetNewsFeed @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val postRepository: PostsRepository
) : Interactor<String, List<Post>> {

    override val dispatcher: CoroutineDispatcher
        get() = dispatcherProvider.provideIoDispatcher()

    override suspend fun invoke(params: String) = postRepository.getPosts(params)
}