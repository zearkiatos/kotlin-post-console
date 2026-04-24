package com.post.console.comment.application

import com.post.console.comment.application.dto.CreateCommentRequest
import com.post.console.comment.application.ports.input.CreateCommentInputPort
import com.post.console.comment.domain.model.Comment
import com.post.console.comment.domain.CommentRepository
import com.post.console.comment.application.mapper.toDomain

class CreateCommentUseCases(
    private val commentRepository: CommentRepository
) : CreateCommentInputPort {
    override fun create(request: CreateCommentRequest): String {
        require(request.id.isNotBlank()) { "ID cannot be blank" }
        require(request.userId.isNotBlank()) { "User ID cannot be blank" }
        require(request.message.isNotBlank()) { "Message cannot be blank" }

        val comment = request.toDomain()
        commentRepository.create(comment)

        return comment.id
    }
}