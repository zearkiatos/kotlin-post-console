package com.post.console.user.application.ports.input

import com.post.console.user.application.dto.CreateUserRequest

interface CreateUserInputPort {
    fun create(request: CreateUserRequest): String
}