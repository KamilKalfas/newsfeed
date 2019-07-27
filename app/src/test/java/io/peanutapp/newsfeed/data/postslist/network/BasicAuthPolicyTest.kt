package io.peanutapp.newsfeed.data.postslist.network

import io.mockk.every
import io.mockk.verify
import io.peanutapp.newsfeed.BaseTest
import io.peanutapp.newsfeed.domain.network.CredentialsProvider
import okhttp3.Interceptor
import okhttp3.Request
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class BasicAuthPolicyTest : BaseTest() {

    private val credentialsProvider: CredentialsProvider<String> = mockk()
    private val credentialsStore: CredentialsStore = mockk()
    private val chain: Interceptor.Chain = mockk()
    private val request: Request = mockk()
    private val requestBuilder: Request.Builder = mockk()
    private val subject = BasicAuthPolicy(credentialsProvider, credentialsStore)

    @Test
    fun `policy includes username and password in header of policy`() {
        val slotHeader = mutableListOf<String>()
        val credentials = "BasicAuthCredentials"
        every { chain.request() } returns request
        every { request.newBuilder() } returns requestBuilder
        every { requestBuilder.addHeader(any(), any()) } returns requestBuilder
        every { requestBuilder.build() } returns request
        every { chain.proceed(any()) } returns mockk()
        every { credentialsProvider.getCredentials(credentialsStore) } returns credentials

        subject.policy.intercept(chain)

        verify(exactly = 1) {
            credentialsProvider.getCredentials(credentialsStore)
            request.newBuilder()
            requestBuilder.addHeader(capture(slotHeader), capture(slotHeader))
            requestBuilder.build()
        }
        assertThat(slotHeader[0]).isEqualTo("Authorization")
        assertThat(slotHeader[1]).isEqualTo(credentials)
    }
}