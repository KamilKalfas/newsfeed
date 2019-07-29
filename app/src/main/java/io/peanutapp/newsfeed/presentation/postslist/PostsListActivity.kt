package io.peanutapp.newsfeed.presentation.postslist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import io.peanutapp.newsfeed.R
import io.peanutapp.newsfeed.databinding.ActivityPostsListBinding
import javax.inject.Inject

class PostsListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val postsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[PostsListViewModel::class.java]
    }

    private lateinit var binding: ActivityPostsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_posts_list)
        binding.view = postsViewModel.view.apply {
            bind(this@PostsListActivity, postsViewModel)
        }
    }
}
