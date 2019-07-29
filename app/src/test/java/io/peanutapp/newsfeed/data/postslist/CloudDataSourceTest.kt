package io.peanutapp.newsfeed.data.postslist

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.slot
import io.peanutapp.newsfeed.BaseTest
import io.peanutapp.newsfeed.data.postslist.entity.FeedResponse
import io.peanutapp.newsfeed.data.postslist.entity.Paging
import io.peanutapp.newsfeed.data.postslist.entity.PostEntity
import io.peanutapp.newsfeed.domain.network.PostsService
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CloudDataSourceTest : BaseTest() {

    private val postsService: PostsService = mockk()
    private val subject = CloudDataSource(postsService)

    @Test
    fun `get calls PostsService and transforms result into posts collection`() {
        val slot = slot<String>()
        val params = "params"
        val response = FeedResponse(
            listOf(
                PostEntity("1", "author1", "title1", "body1"),
                PostEntity("2", "author2", "title2", "body2")
            ),
            Paging("123")
        )
        coEvery { postsService.getPosts(any()) } returns response

        val result = runBlocking { subject.get(params) }

        coVerify { postsService.getPosts(capture(slot)) }
        assertThat(slot.captured).isEqualTo(params)
        assertThat(result.posts).hasSize(2)
        assertThat(result.posts[0].author).isEqualTo("author1")
        assertThat(result.posts[0].title).isEqualTo("title1")
        assertThat(result.posts[0].body).isEqualTo("body1")
        assertThat(result.posts[1].author).isEqualTo("author2")
        assertThat(result.posts[1].title).isEqualTo("title2")
        assertThat(result.posts[1].body).isEqualTo("body2")
        assertThat(result.paginationCursor).isEqualTo("123")
    }
}