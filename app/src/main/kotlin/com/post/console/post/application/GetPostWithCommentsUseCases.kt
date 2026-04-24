package com.post.console.post.application

import com.post.console.post.application.ports.input.GetPostWithCommentsInputPort
import com.post.console.post.application.dto.PostWithCommentsResponse
import com.post.console.post.application.mapper.toResponseWithRelationship
import com.post.console.post.application.mapper.toPostWithCommentsResponse
import com.post.console.post.domain.PostRepository
import com.post.console.user.domain.UserRepository
import com.post.console.comment.domain.CommentRepository

class GetPostWithCommentsUseCases(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val commentRepository: CommentRepository
) : GetPostWithCommentsInputPort {
    override fun getPostWithComments(postId: String): PostWithCommentsResponse? {
        val post = postRepository.get(postId) ?: return null

        val owner = userRepository.get(post.authorId) ?: return null

        val comments = post.comments.mapNotNull() { commentId ->
            val comment = commentRepository.get(commentId) ?: return null
            comment.let {
                if (owner.blockedUsers.contains(comment.userId)) {
                    null
                }
                else {
                    it.toResponseWithRelationship(owner)
                }
            }
        }

        return post.toPostWithCommentsResponse(comments)
    }
}