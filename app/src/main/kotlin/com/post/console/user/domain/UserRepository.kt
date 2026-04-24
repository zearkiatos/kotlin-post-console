package com.post.console.user.domain

import com.post.console.user.domain.model.User

interface UserRepository {
    fun create(user: User)
    fun get(): List<User>
    fun delete(id: String)
    fun get(id: String): User?
    fun update(user: User)
}