package io.peanutapp.newsfeed.data.network

import io.mockk.every
import io.peanutapp.newsfeed.BaseTest
import io.peanutapp.newsfeed.domain.network.AuthPolicy
import okhttp3.Interceptor
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.concurrent.TimeUnit

class PostsServiceConfigTest : BaseTest() {

    private val authPolicy: AuthPolicy<Interceptor> = mockk()
    private val subject = PostsServiceConfig(authPolicy)

    @Test
    fun `host value points into correct url`() {
        assertThat(subject.host).isEqualTo("http://exercice.production.backend.teampeanut.com")
    }

    @Test
    fun `timeouts values are 10 seconds`() {
        assertThat(subject.timeouts.connectTimeout).isEqualTo(10 to TimeUnit.SECONDS)
        assertThat(subject.timeouts.writeTimeout).isEqualTo(10 to TimeUnit.SECONDS)
        assertThat(subject.timeouts.readTimeout).isEqualTo(10 to TimeUnit.SECONDS)
    }

    @Test
    fun `authPolicy returns interceptor as policy`() {
        every { authPolicy.policy } returns mockk()
        assertThat(subject.authPolicy.policy).isInstanceOf(Interceptor::class.java)
    }
}