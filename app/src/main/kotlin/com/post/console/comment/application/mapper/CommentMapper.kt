package com.post.console.comment.application.mapper

import com.post.console.comment.domain.model.Comment
import com.post.console.comment.application.dto.CommentResponse
import com.post.console.comment.application.dto.CreateCommentRequest

fun CreateCommentRequest.toDomain(): Comment {
    return Comment(
        id = id.trim(),
        userId = userId.trim(),
        message = message.trim()
    )
}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = id,
        userId = userId,
        message = message
    )
}