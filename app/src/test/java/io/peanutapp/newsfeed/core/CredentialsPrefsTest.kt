package io.peanutapp.newsfeed.core

import android.content.Context
import android.content.SharedPreferences
import io.mockk.every
import io.mockk.slot
import io.mockk.verify
import io.peanutapp.newsfeed.BaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

private const val PASSWORD_KEY = "key_pwd"
private const val PASSWORD = "pwd"
private const val USER_KEY = "key_usr"
private const val USER = "usr"

class CredentialsPrefsTest : BaseTest() {
    private val context: Context = mockk()
    private val preferences: SharedPreferences = mockk()
    private val editor: SharedPreferences.Editor = mockk()
    private val subject = CredentialsPrefs.Impl(context)

    @Before
    fun setup() {
        every { context.getSharedPreferences(any(), any()) } returns preferences
    }

    @Test
    fun `credentialsPrefs when initialised then calls getSharedPreferences with correct file name and mode`() {
        subject.getUser()

        val fileNameSlot = slot<String>()
        val modeSlot = slot<Int>()
        verify(exactly = 1) {
            context.getSharedPreferences(
                capture(fileNameSlot),
                capture(modeSlot)
            )
        }
        assertThat(fileNameSlot.captured).isEqualTo("CREDENTIALS_PREFS")
        assertThat(modeSlot.captured).isEqualTo(0)
    }

    @Test
    fun `saveUser when called then puts user into prefs file`() {
        val capturedValues = mutableListOf<String>()
        every { preferences.edit() } returns editor
        every { editor.putString(any(), any()) } returns editor

        subject.saveUser(USER)

        verify(exactly = 1) {
            preferences.edit()
            editor.putString(capture(capturedValues), capture(capturedValues))
        }
        assertThat(capturedValues[0]).isEqualTo(USER_KEY)
        assertThat(capturedValues[1]).isEqualTo(USER)
    }

    @Test
    fun `savePassword when called then puts password into prefs file`() {
        val capturedValues = mutableListOf<String>()
        every { preferences.edit() } returns editor
        every { editor.putString(any(), any()) } returns editor

        subject.savePassword(PASSWORD)

        verify(exactly = 1) {
            preferences.edit()
            editor.putString(capture(capturedValues), capture(capturedValues))
        }
        assertThat(capturedValues[0]).isEqualTo(PASSWORD_KEY)
        assertThat(capturedValues[1]).isEqualTo(PASSWORD)
    }

    @Test
    fun `getUser when user have been saved then returns value`() {
        val capturedValues = mutableListOf<String>()
        every { preferences.getString(any(), any()) } returns USER

        assertThat(subject.getUser()).isEqualTo(USER)
        verify { preferences.getString(
            capture(capturedValues),
            capture(capturedValues)) 
        }
        assertThat(capturedValues[0]).isEqualTo(USER_KEY)
        assertThat(capturedValues[1]).isEmpty()
    }

    @Test
    fun `getPassword when password have been saved then returns value`() {
        val password = "password"
        val capturedValues = mutableListOf<String>()
        every { preferences.getString(any(), any()) } returns password

        assertThat(subject.getPassword()).isEqualTo(password)
        verify { preferences.getString(
            capture(capturedValues),
            capture(capturedValues))
        }
        assertThat(capturedValues[0]).isEqualTo(PASSWORD_KEY)
        assertThat(capturedValues[1]).isEmpty()
    }
}