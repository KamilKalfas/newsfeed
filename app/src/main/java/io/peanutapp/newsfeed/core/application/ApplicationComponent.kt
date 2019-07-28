package io.peanutapp.newsfeed.core.application

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModuleBinds::class,
        ApplicationModule::class
    ])
interface ApplicationComponent : AndroidInjector<PostsApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: PostsApplication): ApplicationComponent
    }
}