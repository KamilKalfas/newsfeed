package io.peanutapp.newsfeed.core.application

import android.app.Application
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModuleBinds {

    @Binds
    abstract fun provideApplication(bind: PostsApplication): Application
}