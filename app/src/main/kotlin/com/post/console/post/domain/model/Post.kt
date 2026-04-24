
package com.post.console.post.domain.model

data class Post(
    val id: String,
    val title: String,
    val content: String,
    val authorId: String,
    val comments: MutableList<String>
)