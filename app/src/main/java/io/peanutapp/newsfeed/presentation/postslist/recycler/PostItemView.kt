package io.peanutapp.newsfeed.presentation.postslist.recycler

import androidx.databinding.ObservableField
import javax.inject.Inject

class PostItemView @Inject constructor() {
    val header = ObservableField<String>()
    val body = ObservableField<String>()

    fun ObservableField<String>.formattedHeader(title: String, author: String) {
        this.set("$title \n by $author")
    }
}