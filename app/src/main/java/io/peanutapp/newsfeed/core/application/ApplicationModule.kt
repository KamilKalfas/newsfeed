package io.peanutapp.newsfeed.core.application

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
object ApplicationModule {

    @Provides
    @JvmStatic
    fun provideContext(application: PostsApplication): Context = application.applicationContext
}