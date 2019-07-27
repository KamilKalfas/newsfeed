package io.peanutapp.newsfeed.domain.postslist

import io.mockk.every
import io.mockk.slot
import io.mockk.verify
import io.peanutapp.newsfeed.BaseTest
import io.peanutapp.newsfeed.domain.postslist.GetNewsFeed
import io.peanutapp.newsfeed.domain.PostsRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GetNewsFeedTest : BaseTest() {

    private val postRepository: PostsRepository = mockk()
    private val subject = GetNewsFeed(postRepository)

    @Test
    fun `run calls repository with params`() {
        val params = "params"
        val slot = slot<String>()
        every { postRepository.getPosts(any()) } returns listOf()

        subject.run(params)

        verify(exactly = 1) { postRepository.getPosts(capture(slot)) }
        assertThat(slot.captured).isEqualTo(params)
    }
}