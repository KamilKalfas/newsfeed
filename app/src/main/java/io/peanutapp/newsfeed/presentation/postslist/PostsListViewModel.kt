package io.peanutapp.newsfeed.presentation.postslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.peanutapp.newsfeed.core.ViewContract
import io.peanutapp.newsfeed.domain.ResultState
import io.peanutapp.newsfeed.domain.postslist.GetNewsFeed
import io.peanutapp.newsfeed.domain.postslist.entity.PostsRepositoryResult
import io.peanutapp.newsfeed.domain.then
import io.peanutapp.newsfeed.domain.workAsync
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostsListViewModel @Inject constructor(
    private val getNewsFeed: GetNewsFeed,
    private val postsListView: PostsListView
) : ViewModel(), ViewContract.Callback {

    val view get() = postsListView

    override fun onViewBind() {
        postsListView.changeState(PostsListView.State.Init(
            fetchPostsAction = {cursor -> fetchData(cursor)}
        ))
        fetchData("")
    }

    private fun fetchData(cursor: String) {
        viewModelScope.launch {
            workAsync(getNewsFeed, cursor) then {
                when (it) {
                    is ResultState.Success<*> -> {
                        val data = it.data as PostsRepositoryResult
                        val params = PostsListView.State.DataReceived(data.posts, data.paginationCursor)
                        postsListView.changeState(params)
                    }
                    is ResultState.Failure -> postsListView.changeState(PostsListView.State.Error(it.error))
                }

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        postsListView.unbind()
    }
}