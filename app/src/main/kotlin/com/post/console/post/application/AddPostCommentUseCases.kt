package com.post.console.post.application

import com.post.console.post.application.prots.input.UpdatePostInputPort
import com.post.console.post.domain.PostRepository
import com.post.console.comment.domain.CommentRepository

class AddPostCommentUseCases(
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository
) : UpdatePostInputPort {
    override fun addComment(postId: String, commentId: String) {
        require(commentId.isNotBlank()) { "Comment ID cannot be blank" }
        require(postId.isNotBlank()) { "Post ID cannot be blank" }

        commentRepository.get(commentId) ?: throw IllegalArgumentException("Comment not found")
        
        val post = postRepository.get(postId) ?: throw IllegalArgumentException("Post not found")
        
        post.comments.add(commentId)
        postRepository.update(post)
    }
}