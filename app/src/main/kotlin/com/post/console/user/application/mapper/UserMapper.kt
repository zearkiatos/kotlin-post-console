package com.post.console.user.application.mapper

import com.post.console.shared.relationship.domain.model.Relationship
import com.post.console.user.application.dto.CreateUserRequest
import com.post.console.user.application.dto.UserResponse
import com.post.console.user.domain.model.User

fun CreateUserRequest.toDomain(): User {
    val mutableRelationshipUser = relationshipUser.mapValues { (_, value) ->
        when (value.uppercase()) {
            "FRIEND" -> Relationship.FRIEND
            "COWORKER" -> Relationship.COWORKER
            else -> Relationship.UNKNOWN
        }
    }.toMutableMap()
    return User(
            id = id.trim(),
            name = name.trim(),
            relationshipUser = mutableRelationshipUser,
            blockedUsers = blockedUsers.toMutableSet()
    )
}

fun User.toResponse(): UserResponse {
    return UserResponse(
            id = id,
            name = name,
            relationshipUser = relationshipUser.mapValues { (_, value) ->
                when (value) {
                    Relationship.FRIEND -> "FRIEND"
                    Relationship.COWORKER -> "COWORKER"
                    else -> "UNKNOWN"
                }
            },
            blockedUsers = blockedUsers
    )
}
