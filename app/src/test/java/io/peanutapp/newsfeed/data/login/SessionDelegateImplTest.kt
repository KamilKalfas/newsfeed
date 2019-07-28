package io.peanutapp.newsfeed.data.login

import io.mockk.every
import io.peanutapp.newsfeed.BaseTest
import io.peanutapp.newsfeed.core.CredentialsPrefs
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SessionDelegateImplTest : BaseTest() {

    private val credentialsPrefs: CredentialsPrefs = mockk(relaxed = false)
    private val subject = SessionDelegateImpl(credentialsPrefs)

    @Test
    fun `isUserLogedIn when username and password isEmpty return false`() {
        every { credentialsPrefs.getUser() } returns ""
        every { credentialsPrefs.getPassword() } returns ""

        assertThat(subject.isUserLoggedIn()).isFalse()
    }

    @Test
    fun `isUserLogedIn when username isEmpty and password isNot return false`() {
        every { credentialsPrefs.getUser() } returns ""
        every { credentialsPrefs.getPassword() } returns "password"

        assertThat(subject.isUserLoggedIn()).isFalse()
    }

    @Test
    fun `isUserLogedIn when password isEmpty and username isNot return false`() {
        every { credentialsPrefs.getUser() } returns "username"
        every { credentialsPrefs.getPassword() } returns ""

        assertThat(subject.isUserLoggedIn()).isFalse()
    }

    @Test
    fun `isUserLogedIn when username and password areNotEmpty return true`() {
        every { credentialsPrefs.getUser() } returns "username"
        every { credentialsPrefs.getPassword() } returns "password"

        assertThat(subject.isUserLoggedIn()).isTrue()
    }
}