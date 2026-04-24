package com.post.console.post.application.dto

data class CreatePostRequest(
    val id: String,
    val title: String,
    val content: String,
    val authorId: String,
    val comments: MutableList<String>
)