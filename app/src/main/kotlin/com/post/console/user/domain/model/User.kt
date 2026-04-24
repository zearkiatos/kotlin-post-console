package com.post.console.user.domain.model

import com.post.console.shared.relationship.domain.model.Relationship

data class User(
    val id: String,
    val name: String,
    val relationshipUser: MutableMap<String, Relationship>,
    val blockedUsers: MutableSet<String>
)

fun User.getRelationshipWith(userId: String): Relationship {
    return relationshipUser[userId] ?: Relationship.UNKNOWN
}