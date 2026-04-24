package com.post.console.user.application

import com.post.console.user.application.dto.CreateUserRequest
import com.post.console.user.application.dto.UserResponse
import com.post.console.user.application.ports.input.UpdateUserInputPort
import com.post.console.user.domain.UserRepository
import com.post.console.user.application.mapper.toResponse
import com.post.console.user.application.mapper.toDomain
import com.post.console.user.application.dto.LinkUsersRequest
import com.post.console.user.application.dto.BlockUserRequest
import com.post.console.shared.relationship.domain.model.Relationship

class UpdateUseCases(
    private val userRepository: UserRepository
) : UpdateUserInputPort {
    override fun update(request: CreateUserRequest) : UserResponse {
        require(request.id.isNotBlank()) { "ID cannot be blank" }
        require(request.name.isNotBlank()) { "Name cannot be blank" }

        val user = request.toDomain()
        userRepository.update(user)

        return user.toResponse()
    }

    override fun addFriend(request: LinkUsersRequest) {
        val user = userRepository.get(request.userId) ?: throw IllegalArgumentException("User not found")
        val friend = userRepository.get(request.friendId) ?: throw IllegalArgumentException("Friend not found")

        user.relationshipUser[friend.id] = when (request.relationship.uppercase()) {
            "FRIEND" -> Relationship.FRIEND
            "COWORKER" -> Relationship.COWORKER
            else -> Relationship.UNKNOWN
        }
        userRepository.update(user)
    }

    override fun blockUser(request: BlockUserRequest) {
        val user = userRepository.get(request.userId) ?: throw IllegalArgumentException("User not found")
        val blockedUser = userRepository.get(request.blockedUserId) ?: throw IllegalArgumentException("Blocked user not found")

        user.blockedUsers.add(blockedUser.id)
        userRepository.update(user)
    }

    
}