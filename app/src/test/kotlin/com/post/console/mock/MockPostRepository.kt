package com.post.console.mock

import com.post.console.comment.domain.model.Comment
import com.post.console.post.domain.PostRepository
import com.post.console.post.domain.model.Post

class MockPostRepository : PostRepository {

    private val posts = mutableListOf<Post>()

    override fun create(post: Post) {
        posts.add(post)
    }

    override fun get(): List<Post> {
        return posts
    }

    override fun delete(id: String) {
        posts.removeAll { it.id == id }
    }
}