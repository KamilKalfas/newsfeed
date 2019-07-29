package io.peanutapp.newsfeed.core

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.peanutapp.newsfeed.presentation.postslist.recycler.PaginationOnScrollListener

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("layoutManager")
    fun layoutManager(recyclerView: RecyclerView, layoutOrientation: Int) {
        if (layoutOrientation == RecyclerView.HORIZONTAL) {
            recyclerView.layoutManager = LinearLayoutManager(
                recyclerView.context,
                RecyclerView.HORIZONTAL,
                false
            )
        } else if (layoutOrientation == RecyclerView.VERTICAL) {
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        }
    }

    @JvmStatic
    @BindingAdapter("paginationOnScrollListener")
    fun paginationOnScrollListener(recyclerView: RecyclerView, callback: PaginationOnScrollListener.Callback) {
        recyclerView.addOnScrollListener(PaginationOnScrollListener(callback))
    }
}
