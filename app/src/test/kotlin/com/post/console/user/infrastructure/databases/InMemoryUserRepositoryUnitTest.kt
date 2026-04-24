package com.post.console.user.infrastructure.databases

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import com.post.console.user.domain.UserRepository
import com.post.console.user.infrastructure.databases.InMemoryUserRepository
import com.post.console.user.domain.model.User

class InMemoryUserRepositoryUnitTest() {

    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        userRepository = InMemoryUserRepository()
    }

    @Test
    fun `Given a user, when created, then it should be stored in the repository`() {
        val user = User("user1", "John Doe", emptyMap(), emptySet())
        userRepository.create(user)

        val users = userRepository.get()
        assertTrue(users.contains(user))
    }

    @Test
    fun `Given a user, when deleted, then it should be removed from the repository`() {
        val user = User("user2", "Jane Doe", emptyMap(), emptySet())
        userRepository.create(user)

        userRepository.delete(user.id)
        val users = userRepository.get()
        assertFalse(users.contains(user))
    }
}