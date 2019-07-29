package io.peanutapp.newsfeed.presentation.postslist

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.peanutapp.newsfeed.core.ViewContract
import io.peanutapp.newsfeed.domain.postslist.entity.Post
import io.peanutapp.newsfeed.presentation.postslist.recycler.PaginationOnScrollListener
import io.peanutapp.newsfeed.presentation.postslist.recycler.PostItemViewFactory
import io.peanutapp.newsfeed.presentation.postslist.recycler.PostsAdapter
import java.lang.ref.WeakReference
import javax.inject.Inject
import kotlin.properties.Delegates

class PostsListView @Inject constructor(
    val adapter: PostsAdapter,
    private val postItemViewFactory: PostItemViewFactory
) : ViewContract<PostsListView.State> {
    private lateinit var activityRef: WeakReference<AppCompatActivity>
    private var pagination = ""
    var fetchDataAction: (String) -> Unit = {}
        private set

    val callback = object : PaginationOnScrollListener.Callback {
        override fun loadNext() {
            fetchDataAction.invoke(pagination)
        }
    }

    private var state by Delegates.observable<State>(
        initialValue = State.Uninitialised,
        onChange = { _, _, newState -> onChange(newState) }
    )

    override fun bind(activity: AppCompatActivity, callback: ViewContract.Callback) {
        activityRef = WeakReference(activity)
        callback.onViewBind()
    }

    override fun unbind() = activityRef.clear()

    override fun changeState(newState: State) {
        state = newState
    }

    private fun onChange(newState: State) {
        when (newState) {
            is State.Init -> {
                fetchDataAction = newState.fetchPostsAction
            }
            is State.DataReceived -> {
                val mapped = newState.data.asSequence().map {
                    val params = PostItemViewFactory.Params(it.title, it.author, it.body)
                    postItemViewFactory.create(params)
                }.toList()
                adapter.setData(mapped)
                adapter.notifyDataSetChanged()
                pagination = newState.nextPagination
            }
            is State.Error -> {
                activityRef.get()?.run {
                    Toast.makeText(this, "Oh no!\nSomething went wrong!", Toast.LENGTH_SHORT).show()
                }
                newState.cause?.printStackTrace()
            }
        }
    }

    sealed class State {
        object Uninitialised : State()
        data class Init(val fetchPostsAction: (String) -> Unit) : State()
        data class DataReceived(val data: List<Post>, val nextPagination: String) : State()
        data class Error(val cause: Throwable?) : State()
    }
}