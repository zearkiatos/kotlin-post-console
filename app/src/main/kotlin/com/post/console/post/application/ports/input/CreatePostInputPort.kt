package com.post.console.post.application.ports.input

import com.post.console.post.application.dto.CreatePostRequest

interface CreatePostInputPort {
    fun create(request: CreatePostRequest): String
}