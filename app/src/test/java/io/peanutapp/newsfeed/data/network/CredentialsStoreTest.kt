package io.peanutapp.newsfeed.data.network

import io.mockk.verify
import io.peanutapp.newsfeed.BaseTest
import io.peanutapp.newsfeed.core.CredentialsPrefs
import org.junit.Test

class CredentialsStoreTest : BaseTest() {

    private val credentialsPrefs: CredentialsPrefs = mockk()
    private val subject = CredentialsStore(credentialsPrefs)

    @Test
    fun `user value evaluates credentialsPrefs getUser`() {
        subject.password

        verify(exactly = 1) { credentialsPrefs.getUser() }
    }

    @Test
    fun `password value evaluates credentialsPrefs getUser`() {
        subject.password
        verify(exactly = 1) { credentialsPrefs.getPassword() }
    }
}