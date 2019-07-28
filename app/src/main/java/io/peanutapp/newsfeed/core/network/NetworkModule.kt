package io.peanutapp.newsfeed.core.network

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.peanutapp.newsfeed.core.ApplicationScope
import io.peanutapp.newsfeed.core.CredentialsPrefs
import io.peanutapp.newsfeed.data.login.SessionDelegateImpl
import io.peanutapp.newsfeed.data.network.CredentialsProviderImpl
import io.peanutapp.newsfeed.data.network.CredentialsStore
import io.peanutapp.newsfeed.domain.login.SessionDelegate
import io.peanutapp.newsfeed.domain.network.CredentialsProvider
import io.peanutapp.newsfeed.domain.network.NetworkClientFactory
import io.peanutapp.newsfeed.domain.network.NetworkServiceConfig
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideGson() = Gson()

    @Provides
    @JvmStatic
    @Singleton
    fun provideNetworkClientFactory(
        gson: Gson,
        config: NetworkServiceConfig
    ): NetworkClientFactory {
        return NetworkClientFactory(gson, config)
    }

    @Provides
    @JvmStatic
    @Singleton
    fun provideCredentialsProvider(): CredentialsProvider<String> = CredentialsProviderImpl()

    @Provides
    @JvmStatic
    @Singleton
    fun provideCredentialsStore(credentialsPrefs: CredentialsPrefs): CredentialsProvider.Store =
        CredentialsStore(credentialsPrefs)

    @JvmStatic
    @Provides
    @Singleton
    fun provideSessionDelegate(credentialsPrefs: CredentialsPrefs): SessionDelegate {
        return SessionDelegateImpl(credentialsPrefs)
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideCredentialsPrefs(@ApplicationScope context: Context): CredentialsPrefs {
        return CredentialsPrefs.Impl(context)
    }
}