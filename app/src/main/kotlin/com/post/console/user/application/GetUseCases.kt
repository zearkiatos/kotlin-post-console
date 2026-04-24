package com.post.console.user.application

import com.post.console.user.application.dto.CreateUserRequest
import com.post.console.user.application.dto.UserResponse
import com.post.console.user.application.ports.input.GetUserInputPort
import com.post.console.user.domain.UserRepository
import com.post.console.user.application.mapper.toResponse

class GetUseCases(
    private val userRepository: UserRepository
) : GetUserInputPort {

    override fun get(): List<UserResponse> {
        val users = userRepository.get()
        return users.map { it.toResponse() }
    }

    override fun get(id: String): UserResponse? {
        val user = userRepository.get(id)
        return user?.toResponse()
    }

}