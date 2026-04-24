package com.post.console.post.infrastructure.console

import java.util.UUID
import com.post.console.post.application.ports.input.CreatePostInputPort
import com.post.console.post.application.dto.CreatePostRequest
import com.post.console.post.application.ports.input.GetPostInputPort
import com.post.console.post.application.ports.input.GetPostWithCommentsInputPort
import com.post.console.post.application.prots.input.UpdatePostInputPort

class PostConsoleAdapter(
    private val createPost: CreatePostInputPort,
    private val getPort: GetPostInputPort,
    private val getPostWithCommentsPort: GetPostWithCommentsInputPort,
    private val addPostCommentPort: UpdatePostInputPort
) {
    fun run() {
        while (true) {
            println(
                    """
            What option do you want to select:
            1) create a post
            2) list posts
            3) get post by ID
            4) get post with comments
            5) Add comment to post
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
                "4" -> handleGetPostWithComments()
                "5" -> handleAddCommentToPost()
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

    private fun handleGet() {
        val posts = getPort.get()
        if (posts.isEmpty()) {
            println("No posts found.")
        } else {
            posts.forEach { post ->
                println("Id: ${post.id}, Title: ${post.title}, Content: ${post.content}, Author ID: ${post.authorId} ")
            }
        }
    }

    private fun handleGetById() {
        println("Enter the post ID:")
        val id = readlnOrNull()?.trimIndent()

        if (id == null) {
            println("ID is required")
            return
        }

        val post = getPort.get(id)
        if (post == null) {
            println("Post not found.")
        } else {
            println("Id: ${post.id}, Title: ${post.title}, Content: ${post.content}, Author ID: ${post.authorId} ")
        }
    }

    private fun handleGetPostWithComments() {
        println("Enter the post ID:")
        val id = readlnOrNull()?.trimIndent()

        if (id == null) {
            println("ID is required")
            return
        }

        val postWithComments = getPostWithCommentsPort.getPostWithComments(id)
        if (postWithComments == null) {
            println("Post not found.")
        } else {
            println("Id: ${postWithComments.id}, Title: ${postWithComments.title}, Content: ${postWithComments.content}, Author ID: ${postWithComments.authorId} ")
            postWithComments.comments.forEach { comment ->
                println("  Comment: ${comment.message} (by ${comment.authorRelationship})")
            }
        }
    }

    private fun handleAddCommentToPost() {
        println("Enter the post ID:")
        val postId = readlnOrNull()?.trimIndent()
        println("Enter the comment ID:")
        val commentId = readlnOrNull()?.trimIndent()

        if (postId == null || commentId == null) {
            println("Post ID and Comment ID are required")
            return
        }

        try {
            addPostCommentPort.addComment(postId, commentId)
            println("Comment added to post successfully.")
        } catch (e: IllegalArgumentException) {
            println("Error: ${e.message}")
        }
    }

    
}