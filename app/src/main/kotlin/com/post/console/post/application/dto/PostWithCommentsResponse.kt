package com.post.console.post.application.dto

import com.post.console.post.application.dto.CommentWithRelationshipResponse

data class PostWithCommentsResponse(
    val id: String,
    val title: String,
    val content: String,
    val authorId: String,
    val comments: List<CommentWithRelationshipResponse>
)