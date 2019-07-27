package io.peanutapp.newsfeed.data

import io.peanutapp.newsfeed.BaseTest
import io.peanutapp.newsfeed.domain.CloudDataSource
import io.peanutapp.newsfeed.domain.network.PostsService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PostDataSourceFactoryImplTest : BaseTest() {

    private val postsService: PostsService = mockk()
    private val subject = PostDataSourceFactoryImpl(postsService)

    @Test
    fun `create returns cloud datasource`() {
        assertThat(subject.create()).isInstanceOf(CloudDataSource::class.java)
    }
}