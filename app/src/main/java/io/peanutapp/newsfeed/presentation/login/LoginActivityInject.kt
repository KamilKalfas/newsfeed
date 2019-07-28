package io.peanutapp.newsfeed.presentation.login

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.peanutapp.newsfeed.core.ActivityScope
import io.peanutapp.newsfeed.core.CredentialsPrefs
import io.peanutapp.newsfeed.core.DispatcherProvider
import io.peanutapp.newsfeed.data.login.LoginDelegateImpl
import io.peanutapp.newsfeed.domain.Interactor
import io.peanutapp.newsfeed.domain.login.Login
import io.peanutapp.newsfeed.domain.login.LoginDelegate

@Module
internal abstract class LoginActivityBuilder {

    @ContributesAndroidInjector(
        modules = [LoginActivityModule::class]
    )
    internal abstract fun loginActivity(): LoginActivity
}

@Module(includes = [LoginActivityBinds::class])
object LoginActivityModule {

    @JvmStatic
    @Provides
    fun provideLoginDelegate(credentialsPrefs: CredentialsPrefs) : LoginDelegate {
        return LoginDelegateImpl(credentialsPrefs)
    }
}

@Module
abstract class LoginActivityBinds {
    @Binds
    @ActivityScope
    abstract fun bindContext(activity: LoginActivity): Context

    @Binds
    abstract fun bindLogin(login: Login) : Interactor<Login.Params, Unit>
}