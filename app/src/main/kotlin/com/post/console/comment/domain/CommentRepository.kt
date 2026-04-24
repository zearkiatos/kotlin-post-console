package com.post.console.comment.domain

import com.post.console.comment.domain.model.Comment

interface CommentRepository {
    fun create(comment: Comment)
    fun get(): List<Comment>
    fun delete(id: String)
    fun get(id: String): Comment?
}