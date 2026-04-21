package com.post.console.user.application.dto

data class BlockUserRequest(
    val userId: String,
    val blockedUserId: String
)