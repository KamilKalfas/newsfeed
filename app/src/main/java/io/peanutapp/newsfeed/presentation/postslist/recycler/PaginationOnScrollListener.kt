package io.peanutapp.newsfeed.presentation.postslist.recycler

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val ITEMS_PER_REQUEST = 10

class PaginationOnScrollListener(
    private val callback: Callback
) : RecyclerView.OnScrollListener() {

    @FunctionalInterface
    interface Callback {
        fun loadNext()
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (recyclerView.scrollState == RecyclerView.SCROLL_STATE_SETTLING
            && recyclerView.layoutManager is LinearLayoutManager
        ) {
            (recyclerView.layoutManager as LinearLayoutManager).apply {
                val firstVisibleIndex = findFirstVisibleItemPosition()
                val visibleItems = childCount
                val totalItems = itemCount
                if ((visibleItems + firstVisibleIndex >= totalItems)
                    && totalItems >= ITEMS_PER_REQUEST
                    && firstVisibleIndex >= 0
                ) {
                    callback.loadNext()
                }
            }
        }
        super.onScrolled(recyclerView, dx, dy)
    }
}