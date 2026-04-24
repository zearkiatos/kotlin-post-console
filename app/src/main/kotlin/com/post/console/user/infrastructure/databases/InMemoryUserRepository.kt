package com.post.console.user.infrastructure.databases

import com.post.console.user.domain.UserRepository
import com.post.console.user.domain.model.User

class InMemoryUserRepository : UserRepository {
    private val users = mutableListOf<User>()

    override fun create(user: User) {
        users.add(user)
    }

    override fun get(): List<User> {
        return users
    }

    override fun delete(id: String) {
        users.removeAll { it.id == id }
    }

    override fun get(id: String): User? {
        return users.find { it.id == id }
    }

    override fun update(user: User) {
        val index = users.indexOfFirst { it.id == user.id }
        if (index != -1) {
            users[index] = user
        }
    }

}