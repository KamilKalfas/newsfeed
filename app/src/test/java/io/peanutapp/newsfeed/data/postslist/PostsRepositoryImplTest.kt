package io.peanutapp.newsfeed.data.postslist

import io.mockk.*
import io.peanutapp.newsfeed.BaseTest
import io.peanutapp.newsfeed.data.postslist.PostsRepositoryImpl
import io.peanutapp.newsfeed.domain.PostsDataSource
import io.peanutapp.newsfeed.domain.PostsDataSourceFactory
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PostsRepositoryImplTest : BaseTest() {

    private val postsDataSourceFactory: PostsDataSourceFactory = mockk()
    private val postsDataSource: PostsDataSource = mockk()
    private val subject = PostsRepositoryImpl(postsDataSourceFactory)

    @Test
    fun `getPosts calls datasource with right params and returns posts collection`() {
        val params = "params"
        val slot = slot<String>()
        every { postsDataSourceFactory.create() } returns postsDataSource
        coEvery { postsDataSource.get(any()) } returns listOf()

        runBlocking { subject.getPosts(params) }

        coVerify(exactly = 1) {
            postsDataSourceFactory.create()
            postsDataSource.get(capture(slot))
        }
        assertThat(slot.captured).isEqualTo(params)
    }
}