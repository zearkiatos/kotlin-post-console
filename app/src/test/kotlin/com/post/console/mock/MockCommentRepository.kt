package com.post.console.mock

import com.post.console.comment.domain.model.Comment
import com.post.console.comment.domain.CommentRepository
import com.post.console.post.domain.model.Post

class MockCommentRepository : CommentRepository {

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
}