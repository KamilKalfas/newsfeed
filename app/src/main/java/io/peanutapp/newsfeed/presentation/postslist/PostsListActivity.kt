package io.peanutapp.newsfeed.presentation.postslist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import io.peanutapp.newsfeed.R
import io.peanutapp.newsfeed.core.DispatcherProvider
import io.peanutapp.newsfeed.domain.postslist.GetNewsFeed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class PostsListActivity : AppCompatActivity(), CoroutineScope {

    @Inject
    lateinit var dispatcherProvider: DispatcherProvider

    @Inject
    lateinit var getNewsFeed: GetNewsFeed

    override val coroutineContext: CoroutineContext
        get() = Job() + dispatcherProvider.provideMainDispatcher()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_posts_list)
    }
}
