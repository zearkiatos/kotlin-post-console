package com.post.console.post.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test


class PostUnitTest {
    @Test
    fun `Given a post, when created, then the properties should be correct`() {
        val id = "post1"
        val comments = listOf("comment1", "comment2")

        val post = Post(id, comments)

        assertEquals(id, post.id)
        assertEquals(comments, post.comments)
    }
}