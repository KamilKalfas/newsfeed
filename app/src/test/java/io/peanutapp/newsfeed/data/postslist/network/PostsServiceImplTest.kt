package io.peanutapp.newsfeed.data.postslist.network

import io.mockk.every
import io.mockk.slot
import io.mockk.verify
import io.peanutapp.newsfeed.BaseTest
import io.peanutapp.newsfeed.data.postslist.entity.FeedResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PostsServiceImplTest : BaseTest() {

    private val postsApi : PostsApi = mockk()
    private val subject = PostsServiceImpl(postsApi)

    @Test
    fun `getPosts calls PostAPI with params and returns feed response`() {
        val params = "params"
        val slot = slot<String>()
        every { postsApi.getPosts(any()) } returns mockk()

        assertThat(subject.getPosts(params)).isInstanceOfAny(FeedResponse::class.java)

        verify(exactly = 1) { postsApi.getPosts(capture(slot)) }
        assertThat(slot.captured).isEqualTo(params)
    }
}