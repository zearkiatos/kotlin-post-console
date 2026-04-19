package com.post.console.comment.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class CommentUnitTest {
    @Test
    fun `Given a comment, when created, then the properties should be correct`() {
        val id = "comment1"
        val userId = 123
        val message = "This is a comment"

        val comment = Comment(id, userId, message)

        assertEquals(id, comment.id)
        assertEquals(userId, comment.userId)
        assertEquals(message, comment.message)
    }
}