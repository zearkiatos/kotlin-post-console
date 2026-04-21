package com.post.console.comment.infrastructure.databases


import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import com.post.console.comment.infrastructure.databases.InMemoryCommentRepository
import com.post.console.comment.domain.model.Comment

class InMemoryCommentRepositoryUnitTest() {

    private lateinit var repository: InMemoryCommentRepository

    @Before
    fun setUp() {
        repository = InMemoryCommentRepository()
    }

    @Test
    fun `Given a comment, when created, then it should be retrievable`() {
        val comment = Comment("comment1", 123, "This is a comment")
        repository.create(comment)

        val comments = repository.get()
        assertEquals(1, comments.size)
        assertEquals(comment, comments[0])
    }

    @Test
    fun `Given a comment, when deleted, then it should not be retrievable`() {
        val comment = Comment("comment1", 123, "This is a comment")
        repository.create(comment)

        repository.delete("comment1")
        val comments = repository.get()
        assertTrue(comments.isEmpty())
    }
}