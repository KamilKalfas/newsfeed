package io.peanutapp.newsfeed.data.network

import io.mockk.every
import io.mockk.verify
import io.peanutapp.newsfeed.BaseTest
import io.peanutapp.newsfeed.domain.network.CredentialsProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CredentialsProviderImplTest : BaseTest() {

    private val store: CredentialsProvider.Store = mockk()
    private val subject = CredentialsProviderImpl()

    @Test
    fun `getCredentials calls credentials store and returns credentials`() {
        every { store.user } returns "username"
        every { store.password } returns "password"

        assertThat(subject.getCredentials(store)).isEqualTo("Basic dXNlcm5hbWU6cGFzc3dvcmQ=")
        verify(exactly = 1) {
            store.user
            store.password
        }
    }
}