package com.post.console.comment.infrastructure.databases

import com.post.console.comment.domain.CommentRepository
import com.post.console.comment.domain.model.Comment

class InMemoryCommentRepository : CommentRepository {

    private val comments = mutableListOf<Comment>()

    override fun create(comment: Comment) {
        comments.add(comment)
    }

    override fun get(): List<Comment> {
        return comments
    }

    override fun delete(id: String) {
        comments.removeAll { it.id == id }
    }

    override fun get(id: String): Comment? {
        return comments.find { it.id == id }
    }
}