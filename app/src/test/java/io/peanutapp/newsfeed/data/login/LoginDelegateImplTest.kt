package io.peanutapp.newsfeed.data.login

import io.mockk.verify
import io.peanutapp.newsfeed.BaseTest
import io.peanutapp.newsfeed.core.CredentialsPrefs
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class LoginDelegateImplTest : BaseTest() {

    private val credentialsPrefs: CredentialsPrefs = mockk()
    private val subject = LoginDelegateImpl(credentialsPrefs)

    @Test
    fun `login saves username and password into credentialsPrefs`() {
        val username = "name"
        val password = "PaSwoRd"
        val capturedValues = mutableListOf<String>()

        subject.login(username, password)

        verify(exactly = 1) {
            credentialsPrefs.saveUser(capture(capturedValues))
            credentialsPrefs.savePassword(capture(capturedValues))
        }
        assertThat(capturedValues[0]).isEqualTo(username)
        assertThat(capturedValues[1]).isEqualTo(password)
    }
}