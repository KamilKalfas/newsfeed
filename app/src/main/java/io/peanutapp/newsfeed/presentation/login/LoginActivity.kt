package io.peanutapp.newsfeed.presentation.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import io.peanutapp.newsfeed.R
import io.peanutapp.newsfeed.core.DispatcherProvider
import io.peanutapp.newsfeed.databinding.ActivityLoginBinding
import javax.inject.Inject

class LoginActivity : AppCompatActivity(){

    @Inject
    lateinit var dispatcherProvider: DispatcherProvider

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val loginViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[LoginViewModel::class.java]
    }

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.loginView = loginViewModel.view.apply {
            bind(this@LoginActivity, loginViewModel)
        }

    }
}