package com.post.console.user.infrastructure.console

import java.util.UUID
import com.post.console.user.application.ports.input.CreateUserInputPort
import com.post.console.user.application.dto.CreateUserRequest
import com.post.console.user.application.ports.input.GetUserInputPort
import com.post.console.user.application.ports.input.UpdateUserInputPort
import com.post.console.user.application.dto.LinkUsersRequest
import com.post.console.user.application.dto.BlockUserRequest


class UserConsoleAdapter(
        private val createPort: CreateUserInputPort,
        private val getPort: GetUserInputPort,
        private val updatePort: UpdateUserInputPort
) {
    fun run() {
        while (true) {
            println(
                    """
            What option do you want to select:
            1) create a user
            2) get all users
            3) get user by id
            4) update user
            5) add friend
            6) block user
            0) exit
            """.trimIndent()
            )

            val line = readlnOrNull()?.trim()
            if (line == "0") {
                break
            }

            when (line) {
                "1" -> handleCreate()
                "2" -> handleGet()
                "3" -> handleGetById()
                "4" -> handleUpdate()
                "5" -> handleAddFriend()
                "6" -> handleBlockUser()
                else -> println("Unknown command")
            }
        }
    }

    private fun handleCreate() {
        println("Enter user details (name grade):")
        println("Type the user name:")
        val nameInput = readlnOrNull()?.trimIndent()

        if (nameInput == null) {
            println("All fields are required")
            return
        }

        val name = nameInput

        try {
            val id = createPort.create(CreateUserRequest(name = name, relationshipUser = emptyMap(), blockedUsers = emptySet(), id = UUID.randomUUID().toString()))
            println("User created with ID: $id")
        } catch (e: IllegalArgumentException) {
            println("Validation error: ${e.message}")
        }
    }

    private fun handleGet() {
        val users = getPort.get()
        if (users.isEmpty()) {
            println("No users found.")
        } else {
            users.forEach { user ->
                println("Id: ${user.id}, User: ${user.name}, Relationship: ${user.relationshipUser}, Blocked Users: ${user.blockedUsers} ")
            }
        }
    }

    private fun handleGetById() {
        println("Enter the user ID:")
        val id = readlnOrNull()?.trim()

        if (id == null) {
            println("ID is required")
            return
        }

        val user = getPort.get(id)
        if (user == null) {
            println("User not found.")
        } else {
            println("Id: ${user.id}, User: ${user.name}, Relationship: ${user.relationshipUser}, Blocked Users: ${user.blockedUsers} ")
        }
    }

    private fun handleUpdate() {
        println("Enter user details (id name):")
        println("Type the user id:")
        val idInput = readlnOrNull()?.trimIndent()
        println("Type the user name:")
        val nameInput = readlnOrNull()?.trimIndent()

        if (idInput == null || nameInput == null) {
            println("All fields are required")
            return
        }

        val id = idInput
        val name = nameInput

        try {
            val updatedUser = updatePort.update(CreateUserRequest(id = id, name = name, relationshipUser = emptyMap(), blockedUsers = emptySet()))
            println("User updated: Id: ${updatedUser.id}, User: ${updatedUser.name}, Relationship: ${updatedUser.relationshipUser}, Blocked Users: ${updatedUser.blockedUsers} ")
        } catch (e: IllegalArgumentException) {
            println("Validation error: ${e.message}")
        }
    }

    private fun handleAddFriend() {
        println("Enter details to add friend (userId friendId relationship):")
        println("Type the user id:")
        val userIdInput = readlnOrNull()?.trimIndent()
        println("Type the friend id:")
        val friendIdInput = readlnOrNull()?.trimIndent()
        println("Type the relationship (FRIEND or COWORKER):")
        val relationshipInput = readlnOrNull()?.trimIndent()

        if (userIdInput == null || friendIdInput == null || relationshipInput == null) {
            println("All fields are required")
            return
        }

        try {
            updatePort.addFriend(LinkUsersRequest(userId = userIdInput, friendId = friendIdInput, relationship = relationshipInput))
            println("Friend added successfully.")
        } catch (e: IllegalArgumentException) {
            println("Error: ${e.message}")
        }
    }

    private fun handleBlockUser() {
        println("Enter details to block user (userId blockedUserId):")
        println("Type the user id:")
        val userIdInput = readlnOrNull()?.trimIndent()
        println("Type the blocked user id:")
        val blockedUserIdInput = readlnOrNull()?.trimIndent()

        if (userIdInput == null || blockedUserIdInput == null) {
            println("All fields are required")
            return
        }

        try {
            updatePort.blockUser(BlockUserRequest(userId = userIdInput, blockedUserId = blockedUserIdInput))
            println("User blocked successfully.")
        } catch (e: IllegalArgumentException) {
            println("Error: ${e.message}")
        }
    }

}