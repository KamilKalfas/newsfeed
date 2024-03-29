package io.peanutapp.newsfeed.core.application

import android.content.Context
import dagger.Module
import dagger.Provides
import io.peanutapp.newsfeed.core.ApplicationScope
import io.peanutapp.newsfeed.core.CredentialsPrefs
import io.peanutapp.newsfeed.core.DispatcherProvider
import io.peanutapp.newsfeed.data.login.SessionDelegateImpl
import io.peanutapp.newsfeed.data.network.CredentialsProviderImpl
import io.peanutapp.newsfeed.data.network.CredentialsStoreImpl
import io.peanutapp.newsfeed.domain.login.SessionDelegate
import io.peanutapp.newsfeed.domain.network.CredentialsProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.io.File
import javax.inject.Singleton

@Module(includes = [ApplicationModuleBinds::class])
object ApplicationModule {

    @JvmStatic
    @Provides
    @ApplicationScope
    fun provideContext(application: PostsApplication): Context = application.applicationContext

    @Provides
    @Singleton
    @JvmStatic
    fun provideCacheDir(applicationContext: Context): File = applicationContext.cacheDir

    @JvmStatic
    @Provides
    @Singleton
    fun provideDispatcherProvider() = object : DispatcherProvider {
        override fun main(): CoroutineDispatcher = Dispatchers.Main
        override fun io(): CoroutineDispatcher = Dispatchers.IO
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideCredentialsProvider(): CredentialsProvider<String> = CredentialsProviderImpl()


    @JvmStatic
    @Provides
    @Singleton
    fun provideCredentialsPrefs(@ApplicationScope context: Context): CredentialsPrefs {
        return CredentialsPrefs.Impl(context)
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideCredentialsStore(credentialsPrefs: CredentialsPrefs): CredentialsProvider.Store {
        return CredentialsStoreImpl(credentialsPrefs)
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideSessionDelegate(credentialsPrefs: CredentialsPrefs): SessionDelegate {
        return SessionDelegateImpl(credentialsPrefs)
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideIntentFactory(@ApplicationScope context: Context): IntentFactory {
        return IntentFactory.Impl(context)
    }
}