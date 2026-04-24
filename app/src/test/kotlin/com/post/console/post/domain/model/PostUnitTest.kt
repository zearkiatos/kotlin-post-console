package com.post.console.post.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test


class PostUnitTest {
    @Test
    fun `Given a post, when created, then the properties should be correct`() {
        val id = "post1"
        val title = "Post Title"
        val content = "Post Content"
        val authorId = "author1"
        val comments = listOf("comment1", "comment2").toMutableList()

        val post = Post(id, title, content, authorId, comments)

        assertEquals(id, post.id)
        assertEquals(title, post.title)
        assertEquals(content, post.content)
        assertEquals(authorId, post.authorId)
        assertEquals(comments, post.comments)
    }
}