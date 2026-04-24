package com.post.console.comment.application

import com.post.console.comment.domain.CommentRepository
import com.post.console.comment.application.dto.CommentResponse
import com.post.console.comment.application.ports.input.GetCommentInputPort

class GetCommentUseCases(
    private val commentRepository: CommentRepository
) : GetCommentInputPort {
    override fun get(): List<CommentResponse> {
        return commentRepository.get().map { comment ->
            CommentResponse(
                id = comment.id,
                userId = comment.userId,
                message = comment.message
            )
        }
    }

    override fun get(id: String): CommentResponse? {
        val comment = commentRepository.get(id)
        return comment?.let {
            CommentResponse(
                id = it.id,
                userId = it.userId,
                message = it.message
            )
        }
    }
}