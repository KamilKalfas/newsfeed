package io.peanutapp.newsfeed.domain.postslist

import io.peanutapp.newsfeed.core.DispatcherProvider
import io.peanutapp.newsfeed.domain.Interactor
import io.peanutapp.newsfeed.domain.PostsRepository
import io.peanutapp.newsfeed.domain.postslist.entity.Post
import io.peanutapp.newsfeed.domain.postslist.entity.PostsRepositoryResult
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetNewsFeed @Inject constructor(
    private val postRepository: PostsRepository,
    override val dispatchers: DispatcherProvider
) : Interactor<String, PostsRepositoryResult> {

    override suspend fun invoke(params: String) = postRepository.getPosts(params)
}