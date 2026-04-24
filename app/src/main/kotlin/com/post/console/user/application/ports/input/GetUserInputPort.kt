package com.post.console.user.application.ports.input

import com.post.console.user.application.dto.UserResponse

interface GetUserInputPort {
    fun get(): List<UserResponse>
    fun get(id: String): UserResponse?
}