package com.post.console.user.domain

import com.post.console.user.domain.model.User
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import com.post.console.mock.MockUserRepository

class UserRepositoryUnitTest {

    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        userRepository = MockUserRepository()
    }

    @Test
    fun `Given a user, when created, then it should be added to the repository`() {
        val user = User(
            id = "user1",
            name = "John Doe",
            relationshipUser = emptyMap(),
            blockedUsers = emptySet()
        )

        userRepository.create(user)

        val users = userRepository.get()
        assertTrue(users.contains(user))
    }

    @Test
    fun `Given a user, when deleted, then it should be removed from the repository`() {
        val user = User(
            id = "user2",
            name = "Jane Doe",
            relationshipUser = emptyMap(),
            blockedUsers = emptySet()
        )

        userRepository.create(user)
        userRepository.delete(user.id)

        val users = userRepository.get()
        assertFalse(users.contains(user))
    }
}