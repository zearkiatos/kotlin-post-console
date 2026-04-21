package com.post.console.post.infrastrcuture.databases

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import com.post.console.post.domain.model.Post
import com.post.console.post.infrastructure.databases.InMemoryPostRepository

class InMemoryPostRepositoryUnitTest() {

    private lateinit var repository: InMemoryPostRepository

    @Before
    fun setUp() {
        repository = InMemoryPostRepository()
    }

    @Test
    fun `Given a post, when created, then it should be stored in the repository`() {
        val post = Post("post1", listOf("comment1", "comment2"))

        repository.create(post)

        val posts = repository.get()
        assertEquals(1, posts.size)
        assertEquals(post, posts[0])
    }

    @Test
    fun `Given a post, when deleted, then it should be removed from the repository`() {
        val post = Post("post1", listOf("comment1", "comment2"))
        repository.create(post)

        repository.delete("post1")

        val posts = repository.get()
        assertTrue(posts.isEmpty())
    }
}