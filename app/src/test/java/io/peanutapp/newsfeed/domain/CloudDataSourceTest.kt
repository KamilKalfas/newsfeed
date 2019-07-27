package io.peanutapp.newsfeed.domain

import io.mockk.every
import io.mockk.slot
import io.mockk.verify
import io.peanutapp.newsfeed.BaseTest
import io.peanutapp.newsfeed.data.entity.FeedResponse
import io.peanutapp.newsfeed.data.entity.Paging
import io.peanutapp.newsfeed.data.entity.PostEntity
import io.peanutapp.newsfeed.domain.network.PostsService
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
            Paging("")
        )
        every { postsService.getPosts(any()) } returns response

        val result = subject.get(params)

        verify { postsService.getPosts(capture(slot)) }
        assertThat(slot.captured).isEqualTo(params)
        assertThat(result).hasSize(2)
        assertThat(result[0].author).isEqualTo("author1")
        assertThat(result[0].title).isEqualTo("title1")
        assertThat(result[0].body).isEqualTo("body1")
        assertThat(result[1].author).isEqualTo("author2")
        assertThat(result[1].title).isEqualTo("title2")
        assertThat(result[1].body).isEqualTo("body2")
    }
}