package io.peanutapp.newsfeed.presentation.login

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import io.peanutapp.newsfeed.core.ActivityScope
import io.peanutapp.newsfeed.core.CredentialsPrefs
import io.peanutapp.newsfeed.core.DispatcherProvider
import io.peanutapp.newsfeed.core.ViewContract
import io.peanutapp.newsfeed.core.viewmodel.ViewModelFactoryModule
import io.peanutapp.newsfeed.data.login.LoginDelegateImpl
import io.peanutapp.newsfeed.domain.Interactor
import io.peanutapp.newsfeed.domain.login.Login
import io.peanutapp.newsfeed.domain.login.LoginDelegate

@Module
internal abstract class LoginActivityBuilder {

    @ContributesAndroidInjector(
        modules = [
            LoginActivityModule::class,
            ViewModelFactoryModule::class
        ]
    )
    internal abstract fun loginActivity(): LoginActivity
}

@Module(includes = [LoginActivityBinds::class])
object LoginActivityModule {

    @JvmStatic
    @Provides
    fun provideLoginDelegate(credentialsPrefs: CredentialsPrefs): LoginDelegate {
        return LoginDelegateImpl(credentialsPrefs)
    }

    @JvmStatic
    @Provides
    @ActivityScope
    fun provideLoginInteractor(
        dispatcherProvider: DispatcherProvider,
        loginDelegate: LoginDelegate
    ): Interactor<Login.Params, Unit> {
        return Login(dispatcherProvider, loginDelegate)
    }
}

@Module
abstract class LoginActivityBinds {

    @Binds
    @ActivityScope
    abstract fun bindContext(activity: LoginActivity): Context

    @Binds
    abstract fun bindLogin(login: Login): Interactor<Login.Params, Unit>

    @Binds
    abstract fun bindLoginView(loginView: LoginView) : ViewContract<LoginView.State>

    @Binds
    @IntoMap
    @StringKey("LoginViewModel")
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel
}