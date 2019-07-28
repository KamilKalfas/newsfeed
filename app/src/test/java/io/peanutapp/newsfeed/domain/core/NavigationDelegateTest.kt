package io.peanutapp.newsfeed.domain.core

import io.mockk.every
import io.mockk.slot
import io.mockk.verify
import io.peanutapp.newsfeed.BaseTest
import io.peanutapp.newsfeed.core.application.IntentFactory
import io.peanutapp.newsfeed.domain.login.SessionDelegate
import io.peanutapp.newsfeed.presentation.login.LoginActivity
import io.peanutapp.newsfeed.presentation.postslist.PostsListActivity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class NavigationDelegateTest : BaseTest() {

    private val sessionDelegate: SessionDelegate = mockk()
    private val intentFactory: IntentFactory = mockk()
    private val subject = NavigationDelegate.Impl(sessionDelegate, intentFactory)

    @Test
    fun `getActivityIntent when user is not logged in returns intent with LoginActivity`() {
        val slot = slot<Class<Any>>()
        every { sessionDelegate.isUserLoggedIn() } returns false

        subject.getActivityIntent()
        verify(exactly = 1) { intentFactory.create(clazz = capture(slot)) }
        assertThat(slot.captured.simpleName).isEqualTo(LoginActivity::class.java.simpleName)
    }

    @Test
    fun `getActivityIntent when user is logged in returns intent with PostsListActivity`() {
        val slot = slot<Class<Any>>()
        every { sessionDelegate.isUserLoggedIn() } returns true

        subject.getActivityIntent()
        verify(exactly = 1) { intentFactory.create(clazz = capture(slot)) }
        assertThat(slot.captured.simpleName).isEqualTo(PostsListActivity::class.java.simpleName)
    }
}