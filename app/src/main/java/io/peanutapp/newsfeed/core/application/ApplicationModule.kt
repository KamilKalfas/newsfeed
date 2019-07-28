package io.peanutapp.newsfeed.core.application

import android.content.Context
import dagger.Module
import dagger.Provides
import io.peanutapp.newsfeed.core.ApplicationScope

@Module( includes = [ApplicationModuleBinds::class])
class ApplicationModule {

    @Provides
    @ApplicationScope
    fun provideContext(application: PostsApplication): Context = application.applicationContext
}