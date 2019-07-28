package io.peanutapp.newsfeed.data.network

import io.peanutapp.newsfeed.BaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.concurrent.TimeUnit

class PostsServiceConfigTest : BaseTest() {

    private val subject = PostsServiceConfig()

    @Test
    fun `host value points into correct url`() {
        assertThat(subject.host).isEqualTo("http://exercice.production.backend.teampeanut.com")
    }

    @Test
    fun `timeouts values are 10 seconds`() {
        assertThat(subject.timeouts.connectTimeout.value).isEqualTo(10)
        assertThat(subject.timeouts.connectTimeout.timeUnit).isEqualTo(TimeUnit.SECONDS)
        assertThat(subject.timeouts.writeTimeout.value).isEqualTo(10)
        assertThat(subject.timeouts.writeTimeout.timeUnit).isEqualTo(TimeUnit.SECONDS)
        assertThat(subject.timeouts.readTimeout.value).isEqualTo(10)
        assertThat(subject.timeouts.readTimeout.timeUnit).isEqualTo(TimeUnit.SECONDS)
    }
}