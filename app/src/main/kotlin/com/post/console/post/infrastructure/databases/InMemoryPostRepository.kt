package com.post.console.post.infrastructure.databases

import com.post.console.post.domain.PostRepository
import com.post.console.post.domain.model.Post

class InMemoryPostRepository : PostRepository {

    private val posts = mutableListOf<Post>()

    override fun create(post: Post) {
        posts.add(post)
    }

    override fun get(): List<Post> {
        return posts
    }

    override fun get(id: String): Post? {
        return posts.find { it.id == id }
    }

    override fun delete(id: String) {
        posts.removeAll { it.id == id }
    }

    override fun update(post: Post) {
        val index = posts.indexOfFirst { it.id == post.id }
        if (index != -1) {
            posts[index] = post
        }
    }
}