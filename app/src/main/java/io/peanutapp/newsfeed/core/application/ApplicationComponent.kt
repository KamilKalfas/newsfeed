package io.peanutapp.newsfeed.core.application

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.peanutapp.newsfeed.core.network.NetworkModule
import io.peanutapp.newsfeed.core.viewmodel.ViewModelFactoryModule
import io.peanutapp.newsfeed.presentation.core.EmptyActivityBuilder
import io.peanutapp.newsfeed.presentation.login.LoginActivityBuilder
import io.peanutapp.newsfeed.presentation.postslist.PostsListActivityBuilder
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        EmptyActivityBuilder::class,
        LoginActivityBuilder::class,
        PostsListActivityBuilder::class
    ]
)
interface ApplicationComponent : AndroidInjector<PostsApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: PostsApplication): ApplicationComponent
    }
}