package com.post.console.comment.infrastructure.console

import java.util.UUID
import com.post.console.comment.application.ports.input.CreateCommentInputPort
import com.post.console.comment.application.ports.input.GetCommentInputPort
import com.post.console.comment.application.dto.CreateCommentRequest

class CommentConsoleAdapter(
    private val createCommentInputPort: CreateCommentInputPort,
    private val getCommentInputPort: GetCommentInputPort
) {
    fun run() {
        while (true) {
            println(
                    """
            What option do you want to select:
            1) create a comment
            2) list comments
            3) get comment by ID
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
                else -> println("Unknown command")
            }
        }
    }
    private fun handleCreate() {
        println("Enter comment details (userID message):")
        println("Type the user ID:")
        val userIdInput = readlnOrNull()?.trimIndent()
        println("Type the comment message:")
        val messageInput = readlnOrNull()?.trimIndent()

        if (userIdInput == null || messageInput == null) {
            println("All fields are required")
            return
        }

        val userId = userIdInput
        val message = messageInput

        try {
            val id = createCommentInputPort.create(CreateCommentRequest(id = UUID.randomUUID().toString(), userId = userId, message = message))
            println("Comment created with ID: $id")
        } catch (e: IllegalArgumentException) {
            println("Validation error: ${e.message}")
        }
    }

    private fun handleGet() {
        val comments = getCommentInputPort.get()
        if (comments.isEmpty()) {
            println("No comments found")
        } else {
            comments.forEach { comment ->
                println("ID: ${comment.id}, User ID: ${comment.userId}, Message: ${comment.message}")
            }
        }
    }

    private fun handleGetById() {
        println("Type the comment ID:")
        val idInput = readlnOrNull()?.trimIndent()

        if (idInput == null) {
            println("Comment ID is required")
            return
        }

        val comment = getCommentInputPort.get(idInput)
        if (comment == null) {
            println("Comment not found")
        } else {
            println("ID: ${comment.id}, User ID: ${comment.userId}, Message: ${comment.message}")
        }
    }
}
