package com.post.console.shared.relationship.domain.model

import org.junit.Assert.*
import org.junit.Test

class RelationshipUnitTest {
    @Test
    fun `Given a relationship, when created, then the properties should be correct`() {
        val relationship = Relationship.FRIEND
        val expectedValue = "FRIEND"

        assertEquals(expectedValue, relationship.name)
    }

    @Test
    fun `Given a relationship, when created, then the properties should be correct for COWORKER`() {
        val relationship = Relationship.COWORKER
        val expectedValue = "COWORKER"

        assertEquals(expectedValue, relationship.name)
    }

    @Test
    fun `Given a relationship, when created, then the properties should be correct for UNKNOWN`() {
        val relationship = Relationship.UNKNOWN
        val expectedValue = "UNKNOWN"

        assertEquals(expectedValue, relationship.name)
    }
}