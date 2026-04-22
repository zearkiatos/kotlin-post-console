package com.post.console.post.infrastructure.console

import com.post.console.post.application.ports.input.CreatePostInputPort
import com.post.console.post.application.dto.CreatePostRequest
import java.util.UUID

class PostConsoleAdapter(
    private val createPost: CreatePostInputPort
) {
    fun run() {
        while (true) {
            println(
                    """
            What option do you want to select:
            1) create a post
            0) exit
            """.trimIndent()
            )

            val line = readlnOrNull()?.trim()
            if (line == "0") {
                break
            }

            when (line) {
                "1" -> handleCreate()
                else -> println("Unknown command")
            }
        }
    }

    private fun handleCreate() {
        println("Enter post details (title content):")
        println("Type the post title:")
        val titleInput = readlnOrNull()?.trimIndent()
        println("Type the post content:")
        val contentInput = readlnOrNull()?.trimIndent()
        println("Type the author ID:")
        val authorIdInput = readlnOrNull()?.trimIndent()

        if (titleInput == null || contentInput == null || authorIdInput == null) {
            println("All fields are required")
            return
        }

        val title = titleInput
        val content = contentInput
        val authorId = authorIdInput
        val listOfComments = mutableListOf<String>()

        try {
            val id = createPost.create(CreatePostRequest(title = title, content = content, comments = listOfComments, id = UUID.randomUUID().toString(), authorId = authorId))
            println("Post created with ID: $id")
        } catch (e: IllegalArgumentException) {
            println("Validation error: ${e.message}")
        }
    }
}