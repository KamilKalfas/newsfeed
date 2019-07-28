package io.peanutapp.newsfeed.presentation.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import io.peanutapp.newsfeed.domain.core.NavigationDelegate
import javax.inject.Inject

class EmptyActivity : AppCompatActivity() {

    @Inject
    lateinit var navigationDelegate: NavigationDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        startActivity(navigationDelegate.getActivityIntent())
        finish()
    }
}