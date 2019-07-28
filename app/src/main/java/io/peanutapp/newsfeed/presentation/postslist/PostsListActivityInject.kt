package io.peanutapp.newsfeed.presentation.postslist

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.peanutapp.newsfeed.core.ActivityScope
import io.peanutapp.newsfeed.data.postslist.PostsDataSourceFactoryImpl
import io.peanutapp.newsfeed.data.postslist.PostsRepositoryImpl
import io.peanutapp.newsfeed.domain.PostsDataSourceFactory
import io.peanutapp.newsfeed.domain.PostsRepository
import io.peanutapp.newsfeed.domain.network.PostsService

@Module
internal abstract class PostsListActivityBuilder {
    @ContributesAndroidInjector(
        modules = [
            PostsListActivityModule::class
        ]
    )
    internal abstract fun postsListActivity(): PostsListActivity
}

@Module(includes = [PostsListActivityBinds::class])
object PostsListActivityModule {

    @JvmStatic
    @Provides
    fun providesPostsRepository(postsDataSourceFactory: PostsDataSourceFactory): PostsRepository {
        return PostsRepositoryImpl(postsDataSourceFactory)
    }

    @JvmStatic
    @Provides
    fun providePostsDataSourceFactory(postsService: PostsService) : PostsDataSourceFactory {
        return PostsDataSourceFactoryImpl(postsService)
    }
}

@Module
abstract class PostsListActivityBinds {
    @Binds
    @ActivityScope
    abstract fun bindContext(activity: PostsListActivity): Context
}