package com.post.console.comment.application.ports.input

import com.post.console.comment.application.dto.CreateCommentRequest

interface CreateCommentInputPort {
    fun create(request: CreateCommentRequest): String
}