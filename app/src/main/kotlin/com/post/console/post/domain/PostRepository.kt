package com.post.console.post.domain

import com.post.console.post.domain.model.Post

interface PostRepository {
    fun create(post: Post)
    fun get(): List<Post>
    fun delete(id: String)
    fun update(post: Post)
    fun get(id: String): Post?
}