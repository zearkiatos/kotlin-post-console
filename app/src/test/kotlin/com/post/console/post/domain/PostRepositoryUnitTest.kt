package com.post.console.post.domain

import com.post.console.post.domain.model.Post
import com.post.console.post.domain.PostRepository
import com.post.console.mock.MockPostRepository


class PostRepositoryUnitTest {

    private lateinit var postRepository: PostRepository

    @org.junit.Before
    fun setUp() {
        postRepository = MockPostRepository()
    }

    @org.junit.Test
    fun `Given two posts, When created, Then they should be retrievable`() {
        val post1 = Post(id = "1", title = "Post 1", content = "Content 1", authorId = "author1", comments = listOf("comment1", "comment2").toMutableList())
        val post2 = Post(id = "2", title = "Post 2", content = "Content 2", authorId = "author2", comments = listOf("comment3").toMutableList())

        postRepository.create(post1)
        postRepository.create(post2)

        val posts = postRepository.get()
        assert(posts.size == 2)
        assert(posts.contains(post1))
        assert(posts.contains(post2))
    }

    @org.junit.Test
    fun `Given a post, When deleted, Then it should not be retrievable`() {
        val post1 = Post(id = "1", title = "Post 1", content = "Content 1", authorId = "author1", comments = listOf("comment1", "comment2").toMutableList())
        val post2 = Post(id = "2", title = "Post 2", content = "Content 2", authorId = "author2", comments = listOf("comment3").toMutableList())

        postRepository.create(post1)
        postRepository.create(post2)

        postRepository.delete("1")

        val posts = postRepository.get()
        assert(posts.size == 1)
        assert(posts.contains(post2))
    }
}