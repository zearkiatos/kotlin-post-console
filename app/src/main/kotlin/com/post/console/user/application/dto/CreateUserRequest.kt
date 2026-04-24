package com.post.console.user.application.dto

data class CreateUserRequest(
    val id: String,
    val name: String,
    val relationshipUser: Map<String, String>,
    val blockedUsers: Set<String>
)