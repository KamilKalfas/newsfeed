package io.peanutapp.newsfeed.domain.postslist

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.slot
import io.peanutapp.newsfeed.BaseTest
import io.peanutapp.newsfeed.core.DispatcherProvider
import io.peanutapp.newsfeed.domain.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GetNewsFeedTest : BaseTest() {

    private val postRepository: PostsRepository = mockk()
    private val dispatcherProvider: DispatcherProvider = mockk()
    private val subject = GetNewsFeed(dispatcherProvider, postRepository)

    @Test
    fun `invoke calls repository with params`() {
        val params = "params"
        val slot = slot<String>()
        every { dispatcherProvider.provideIoDispatcher() } returns Dispatchers.Unconfined
        coEvery { postRepository.getPosts(any()) } returns listOf()

        runBlocking { subject(params) }

        coVerify(exactly = 1) { postRepository.getPosts(capture(slot)) }
        assertThat(slot.captured).isEqualTo(params)
    }
}