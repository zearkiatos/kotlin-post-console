package com.post.console.comment.application.ports.input

import com.post.console.comment.application.dto.CommentResponse

interface GetCommentInputPort {
    fun get(): List<CommentResponse>
    fun get(id: String): CommentResponse?
}