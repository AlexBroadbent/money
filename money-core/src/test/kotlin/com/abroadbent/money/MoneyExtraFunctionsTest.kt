package com.abroadbent.money

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MoneyExtraFunctionsTest : BaseTest() {

    @Nested
    @DisplayName("Percentage")
    inner class PercentageTest {

        @Test
        fun `percentage with integer`() {
            assertEquals(onePound, tenPounds.percentage(10))
        }

        @Test
        fun `percentage with long`() {
            assertEquals(fivePounds, tenPounds.percentage(50L))
        }
    }

    @Nested
    @DisplayName("Positive")
    inner class IsPositiveTest {

        @Test
        fun `positive check with zero amount`() {
            assertTrue(Money.fromMajor(0, gbp).isPositive())
        }

        @Test
        fun `positive check`() {
            assertTrue(tenPounds.isPositive())
        }

        @Test
        fun `positive operation check`() {
            assertTrue(tenPounds.plus(fivePounds).isPositive())
        }
    }

    @Nested
    @DisplayName("Negative")
    inner class IsNegativeTest {

        @Test
        fun `negative check`() {
            assertFalse(fiftyPence.isNegative())
        }

        @Test
        fun `negative operation check`() {
            assertTrue(fiftyPence.minus(fivePounds).isNegative())
        }
    }
}
