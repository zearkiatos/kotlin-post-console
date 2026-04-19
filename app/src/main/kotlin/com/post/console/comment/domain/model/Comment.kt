package com.post.console.comment.domain.model

data class Comment(
    val id: String,
    val userId: Int,
    val message: String
)