package io.peanutapp.newsfeed.presentation.postslist

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.peanutapp.newsfeed.core.ActicityScope
import io.peanutapp.newsfeed.core.network.NetworkModule
import io.peanutapp.newsfeed.core.network.NetworkModule_ProvideHttpClientFactoryFactory
import io.peanutapp.newsfeed.data.postslist.PostsDataSourceFactoryImpl
import io.peanutapp.newsfeed.data.postslist.PostsRepositoryImpl
import io.peanutapp.newsfeed.data.postslist.network.PostsServiceImpl
import io.peanutapp.newsfeed.domain.Interactor
import io.peanutapp.newsfeed.domain.PostsDataSourceFactory
import io.peanutapp.newsfeed.domain.PostsRepository
import io.peanutapp.newsfeed.domain.network.HttpClientFactory
import io.peanutapp.newsfeed.domain.network.NetworkClientFactory
import io.peanutapp.newsfeed.domain.network.PostsService
import io.peanutapp.newsfeed.domain.network.PostsServiceFactory
import io.peanutapp.newsfeed.domain.postslist.GetNewsFeed
import io.peanutapp.newsfeed.domain.postslist.entity.Post

@Module
internal abstract class PostsListActivityBuilder {
    @ContributesAndroidInjector(modules = [
        NetworkModule::class,
        PostsListActivityModule::class
    ])
    internal abstract fun postsListActivity(): PostsListActivity
}

@Module(includes = [PostsListActivityBinds::class])
object PostsListActivityModule {

    @JvmStatic
    @Provides
    fun provideGetNewsFeed(postsRepository: PostsRepository) : Interactor<String, List<Post>> {
        return GetNewsFeed(postsRepository)
    }

    @JvmStatic
    @Provides
    fun providesPostsRepository(postsDataSourceFactory: PostsDataSourceFactory) : PostsRepository {
        return PostsRepositoryImpl(postsDataSourceFactory)
    }

    @JvmStatic
    @Provides
    fun providePostsServiceFactory(
        httpClientFactory: HttpClientFactory,
        networkClientFactory: NetworkClientFactory
    ) : PostsServiceFactory {
        return PostsServiceFactory(networkClientFactory, httpClientFactory)
    }

    @JvmStatic
    @Provides
    fun providsPostsService(postsServiceFactory: PostsServiceFactory) : PostsService {
        return PostsServiceImpl(postsServiceFactory.create())
    }

    @JvmStatic
    @Provides
    fun providePostsDataSourceFactory(postsService: PostsService) = PostsDataSourceFactoryImpl(postsService)
}

@Module
abstract class PostsListActivityBinds {

    @Binds
    @ActicityScope
    abstract fun bindContext(activity: PostsListActivity): Context
}