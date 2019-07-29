package io.peanutapp.newsfeed.presentation.postslist

import androidx.databinding.ObservableField
import javax.inject.Inject

class PostItemView @Inject constructor() {
    val title = ObservableField<String>()
    val author = ObservableField<String>()
    val body = ObservableField<String>()
}