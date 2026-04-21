package com.post.console.user.application.dto

data class LinkUsersRequest(
    val userId: String,
    val friendId: String,
    val relationship: String
)