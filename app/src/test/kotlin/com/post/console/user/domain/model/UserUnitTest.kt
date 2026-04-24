package com.post.console.user.domain.model

import org.junit.Assert.*
import org.junit.Test
import com.post.console.shared.relationship.domain.model.Relationship


class UserUnitTest {

    @Test
    fun `Given a user, when created, then the properties should be correct`() {
        val id = "user1"
        val name = "John Doe"
        val relationshipUser = mapOf("user2" to Relationship.FRIEND)
        val blockedUsers = setOf("user3")

        val user = User(id, name, relationshipUser, blockedUsers)

        assertEquals(id, user.id)
        assertEquals(name, user.name)
        assertEquals(relationshipUser, user.relationshipUser)
        assertEquals(blockedUsers, user.blockedUsers)
    }
    
}