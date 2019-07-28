package io.peanutapp.newsfeed.presentation.core

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.peanutapp.newsfeed.core.ActivityScope
import io.peanutapp.newsfeed.core.application.IntentFactory
import io.peanutapp.newsfeed.domain.core.NavigationDelegate
import io.peanutapp.newsfeed.domain.login.SessionDelegate

@Module
internal abstract class EmptyActivityBuilder {

    @ContributesAndroidInjector(
        modules = [
            EmptyActivityModule::class
        ]
    )
    internal abstract fun emptyActivity(): EmptyActivity
}

@Module(includes = [EmptyActivityBinds::class])
object EmptyActivityModule {

    @JvmStatic
    @Provides
    fun provideNavigationDelegate(sessionDelegate: SessionDelegate, intentFactory: IntentFactory): NavigationDelegate {
        return NavigationDelegate.Impl(sessionDelegate, intentFactory)
    }
}

@Module
abstract class EmptyActivityBinds {
    @Binds
    @ActivityScope
    abstract fun bindContext(activity: EmptyActivity): Context
}