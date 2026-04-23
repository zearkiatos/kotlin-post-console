package com.post.console.comment.domain

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import com.post.console.comment.domain.CommentRepository
import com.post.console.mock.MockCommentRepository
import com.post.console.comment.domain.model.Comment

class CommentRepositoryUnitTest {

    private lateinit var commentRepository: CommentRepository

    @Before
    fun setUp() {
        commentRepository = MockCommentRepository()
    }

    @Test
    fun `Given two comments, When created, Then they should be retrievable`() {
        val comment1 = Comment(id = "1", userId = "user1", message = "First comment")
        val comment2 = Comment(id = "2", userId = "user2", message = "Second comment")

        commentRepository.create(comment1)
        commentRepository.create(comment2)

        val comments = commentRepository.get()
        assertEquals(2, comments.size)
        assertTrue(comments.contains(comment1))
        assertTrue(comments.contains(comment2))
    }

    @Test
    fun `Given a comment, When deleted, Then it should not be retrievable`() {
        val comment = Comment(id = "1", userId = "user1", message = "First comment")
        commentRepository.create(comment)

        commentRepository.delete("1")

        val comments = commentRepository.get()
        assertFalse(comments.contains(comment))
    }

    @Test
    fun `Given an existing comment, When retrieved by ID, Then it should be returned`() {
        val comment = Comment(id = "1", userId = "user1", message = "First comment")
        commentRepository.create(comment)

        val retrievedComment = commentRepository.get("1")
        assertEquals(comment, retrievedComment)
    }

    @Test
    fun `Given a non-existing comment ID, When retrieved by ID, Then it should return null`() {
        val retrievedComment = commentRepository.get("non-existing")
        assertNull(retrievedComment)
    }
}