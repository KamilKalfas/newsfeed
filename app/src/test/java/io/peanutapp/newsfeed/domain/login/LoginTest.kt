package io.peanutapp.newsfeed.domain.login

import io.mockk.verify
import io.peanutapp.newsfeed.BaseTest
import io.peanutapp.newsfeed.core.DispatcherProvider
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class LoginTest : BaseTest() {

    private val dispatcherProvider: DispatcherProvider = mockk()
    private val loginDelegate: LoginDelegate = mockk()
    private val subject = Login(dispatcherProvider, loginDelegate)

    @Test
    fun `invoke calls loginDelegate login with params`() {
        val capturedValues = mutableListOf<String>()
        val username = "myusername"
        val password = "mypaswword"

        runBlocking { subject(Login.Params(username, password)) }

        verify(exactly = 1) {
            loginDelegate.login(capture(capturedValues), capture(capturedValues))
        }
        assertThat(capturedValues[0]).isEqualTo(username)
        assertThat(capturedValues[1]).isEqualTo(password)
    }

    @Test
    fun `login dispatcher is using IO to work on`() {
        subject.dispatcher
        verify { dispatcherProvider.provideIoDispatcher() }
    }
}