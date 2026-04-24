package com.post.console.user.application.dto

data class UserResponse(
    val id: String,
    val name: String,
    val relationshipUser: Map<String, String>,
    val blockedUsers: Set<String>
)