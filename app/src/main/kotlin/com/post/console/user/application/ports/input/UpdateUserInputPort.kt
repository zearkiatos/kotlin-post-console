package com.post.console.user.application.ports.input

import com.post.console.user.application.dto.CreateUserRequest
import com.post.console.user.application.dto.UserResponse
import com.post.console.user.application.dto.LinkUsersRequest
import com.post.console.user.application.dto.BlockUserRequest

interface UpdateUserInputPort {
    fun update(request: CreateUserRequest) : UserResponse
    fun addFriend(request: LinkUsersRequest)
    fun blockUser(request: BlockUserRequest)
}