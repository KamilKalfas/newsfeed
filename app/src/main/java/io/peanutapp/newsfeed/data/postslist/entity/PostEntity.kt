package io.peanutapp.newsfeed.data.postslist.entity

import io.peanutapp.newsfeed.domain.postslist.entity.Post


data class PostEntity(
    // uid is a required unique field
    val uid : String,
    // author is a required field
    val author: String,
    // title is a required field
    val title: String,
    // body is an optional field that might be missing from the response
    val body: String = ""
)

fun PostEntity.toPost() = Post(author, title, body)