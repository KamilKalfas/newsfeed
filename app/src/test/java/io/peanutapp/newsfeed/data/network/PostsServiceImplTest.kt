package io.peanutapp.newsfeed.data.network

import io.mockk.*
import io.peanutapp.newsfeed.BaseTest
import io.peanutapp.newsfeed.data.postslist.entity.FeedResponse
import io.peanutapp.newsfeed.data.postslist.entity.Paging
import io.peanutapp.newsfeed.data.postslist.entity.PostEntity
import io.peanutapp.newsfeed.domain.postslist.entity.Post
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PostsServiceImplTest : BaseTest() {

    private val postsApi: PostsApi = mockk()
    private val subject = PostsServiceImpl(postsApi)

    @Test
    fun `getPosts calls PostAPI with params and returns feed response`() {
        val params = "params"
        val slot = slot<String>()
        coEvery { postsApi.getPosts(any()) } returns FeedResponse(
            listOf(
                PostEntity("uid", "a", "t", "b")
            ),
            Paging("2")
        )

        val result = runBlocking { subject.getPosts(params) }

        assertThat(result).isInstanceOf(FeedResponse::class.java)
        assertThat(result.posts).hasSize(1)
        assertThat(result.paging.next_cursor).isEqualTo("2")
        coVerify(exactly = 1) { postsApi.getPosts(capture(slot)) }
        assertThat(slot.captured).isEqualTo(params)
    }
}