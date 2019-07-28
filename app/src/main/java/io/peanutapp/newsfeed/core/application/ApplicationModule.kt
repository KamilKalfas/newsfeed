package io.peanutapp.newsfeed.core.application

import android.content.Context
import dagger.Module
import dagger.Provides
import io.peanutapp.newsfeed.core.ApplicationScope
import io.peanutapp.newsfeed.core.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module( includes = [ApplicationModuleBinds::class])
class ApplicationModule {

    @Provides
    @ApplicationScope
    fun provideContext(application: PostsApplication): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideDispatcherProvider() = object : DispatcherProvider {
        override fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
        override fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
    }
}