package io.peanutapp.newsfeed.presentation.postslist.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.peanutapp.newsfeed.R
import io.peanutapp.newsfeed.databinding.ItemFeedPostBinding
import javax.inject.Inject

class PostsAdapter @Inject constructor() : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    private val postItems = mutableListOf<PostItemView>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_feed_post, parent, false))
    }

    override fun getItemCount() = postItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postItems[position])
    }

    fun setData(data: List<PostItemView>) {
        postItems.addAll(data)
    }

    inner class ViewHolder(private val binding: ItemFeedPostBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(itemView: PostItemView) {
            binding.view = itemView
            binding.executePendingBindings()
        }
    }
}