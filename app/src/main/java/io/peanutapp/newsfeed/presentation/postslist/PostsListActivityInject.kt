package io.peanutapp.newsfeed.presentation.postslist

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import io.peanutapp.newsfeed.core.ActivityScope
import io.peanutapp.newsfeed.core.DispatcherProvider
import io.peanutapp.newsfeed.core.ViewContract
import io.peanutapp.newsfeed.core.viewmodel.ViewModelFactoryModule
import io.peanutapp.newsfeed.data.postslist.PostsDataSourceFactoryImpl
import io.peanutapp.newsfeed.data.postslist.PostsRepositoryImpl
import io.peanutapp.newsfeed.domain.Interactor
import io.peanutapp.newsfeed.domain.PostsDataSourceFactory
import io.peanutapp.newsfeed.domain.PostsRepository
import io.peanutapp.newsfeed.domain.network.PostsService
import io.peanutapp.newsfeed.domain.postslist.GetNewsFeed
import io.peanutapp.newsfeed.domain.postslist.entity.Post
import javax.inject.Provider

@Module
internal abstract class PostsListActivityBuilder {
    @ContributesAndroidInjector(
        modules = [
            PostsListActivityModule::class,
            ViewModelFactoryModule::class
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
    fun providePostsDataSourceFactory(postsService: PostsService): PostsDataSourceFactory {
        return PostsDataSourceFactoryImpl(postsService)
    }

    @JvmStatic
    @Provides
    fun providePostItemViewFactory(provider: Provider<PostItemView>) = PostItemViewFactory(provider)

//    @JvmStatic
//    @Provides
//    @ActivityScope
//    fun provideGetNewsFeedInteractor(
//        postsRepository: PostsRepository,
//        dispatcherProvider: DispatcherProvider
//    ): Interactor<String, PostsRepositoryResult> {
//        return GetNewsFeed(postsRepository, dispatcherProvider)
//    }
}

@Module
abstract class PostsListActivityBinds {

    @Binds
    @ActivityScope
    abstract fun bindContext(activity: PostsListActivity): Context

//    @Binds
//    abstract fun bindGetNewsFeed(getNewsFeed: GetNewsFeed): Interactor<String, PostsRepositoryResult>

    @Binds
    abstract fun bindPostsView(postsView: PostsListView): ViewContract<PostsListView.State>

    @Binds
    @IntoMap
    @StringKey("PostsListViewModel")
    abstract fun bindPostsViewModel(viewModel: PostsListViewModel): ViewModel
}