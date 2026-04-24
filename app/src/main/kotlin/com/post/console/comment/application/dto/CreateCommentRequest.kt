package com.post.console.comment.application.dto


data class CreateCommentRequest(
    val id: String,
    val userId: String,
    val message: String
)