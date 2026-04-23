package com.post.console.comment.application.dto

data class CommentResponse(
    val id: String,
    val userId: String,
    val message: String
)