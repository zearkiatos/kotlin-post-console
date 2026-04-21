package com.post.console.user.application

import java.util.UUID
import com.post.console.user.application.dto.CreateUserRequest
import com.post.console.user.application.mapper.toDomain
import com.post.console.user.application.ports.input.CreateUserInputPort
import com.post.console.user.domain.UserRepository

class CreateUseCases(private val userRepository: UserRepository) : CreateUserInputPort {
    override fun create(request: CreateUserRequest): String {
        require(request.id.isNotBlank()) { "ID cannot be blank" }
        require(request.name.isNotBlank()) { "Name cannot be blank" }

        val user = request.toDomain()
        userRepository.create(user)

        return user.id
    }
}
