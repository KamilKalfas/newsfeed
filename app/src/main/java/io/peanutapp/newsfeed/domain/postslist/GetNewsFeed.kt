package io.peanutapp.newsfeed.domain.postslist

import io.peanutapp.newsfeed.core.DispatcherProvider
import io.peanutapp.newsfeed.domain.Interactor
import io.peanutapp.newsfeed.domain.PostsRepository
import io.peanutapp.newsfeed.domain.ResultState
import io.peanutapp.newsfeed.domain.postslist.entity.Post
import io.peanutapp.newsfeed.domain.postslist.entity.PostsRepositoryResult
import io.peanutapp.newsfeed.domain.safeWrap
import kotlinx.coroutines.CoroutineDispatcher
import java.lang.Exception
import javax.inject.Inject

class GetNewsFeed @Inject constructor(
    private val postRepository: PostsRepository,
    override val dispatchers: DispatcherProvider
) : Interactor<String, ResultState> {

    override suspend fun invoke(params: String) = safeWrap { postRepository.getPosts(params) }
}