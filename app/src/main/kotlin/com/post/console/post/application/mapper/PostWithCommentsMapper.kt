package com.post.console.post.application.mapper

import com.post.console.post.domain.model.Post
import com.post.console.post.application.dto.PostWithCommentsResponse
import com.post.console.post.application.dto.CommentWithRelationshipResponse
import com.post.console.comment.domain.model.Comment
import com.post.console.user.domain.model.User
import com.post.console.user.domain.model.getRelationshipWith

fun Comment.toResponseWithRelationship(authorOfPost: User): CommentWithRelationshipResponse {
    return CommentWithRelationshipResponse(
        id = id,
        userId = userId,
        message = message,
        authorRelationship = authorOfPost.getRelationshipWith(userId)
    )
}

fun Post.toPostWithCommentsResponse(commentsWithRelationship: List<CommentWithRelationshipResponse>): PostWithCommentsResponse {
    return PostWithCommentsResponse(
        id = id,
        title = title,
        content = content,
        authorId = authorId,
        comments = commentsWithRelationship
    )
}