package com.post.console.post.application.dto

import com.post.console.shared.relationship.domain.model.Relationship

data class CommentWithRelationshipResponse(
    val id: String,
    val userId: String,
    val message: String,
    val authorRelationship: Relationship
)