package com.post.console.post.application.ports.input

import com.post.console.post.application.dto.PostResponse

interface GetPostInputPort {
    fun get(): List<PostResponse>
    fun get(id: String): PostResponse?
}