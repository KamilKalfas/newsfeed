package io.peanutapp.newsfeed.data.postslist.entity

/**
 * {
 *    "posts":[
 *       {
 *          "uid":"post_uid",
 *          "author":"Sarah",
 *          "title":"Post title",
 *          "body":"Post body"
 *       }
 *    ],
 *    "paging":{
 *       "next_cursor":"a3d"
 *    }
 * }
 */
data class FeedResponse(
    val posts: List<PostEntity>,
    val paging: Paging
)

data class Paging(
    val next_cursor: String
)