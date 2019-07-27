package io.peanutapp.newsfeed.data

import io.peanutapp.newsfeed.domain.Interactor
import io.peanutapp.newsfeed.domain.PostsRepository
import io.peanutapp.newsfeed.domain.entity.Post

class GetNewsFeed(
    private val postRepository: PostsRepository
) : Interactor<String, List<Post>> {

    override fun run(params: String) = postRepository.getPosts(params)
}