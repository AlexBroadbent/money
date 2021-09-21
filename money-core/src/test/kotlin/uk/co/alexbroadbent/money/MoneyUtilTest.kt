package uk.co.alexbroadbent.money

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MoneyUtilTest : BaseTest() {

    @Nested
    @DisplayName("Min")
    inner class MinTest {

        @Test
        fun `check min`() {
            assertEquals(twoPounds, Money.min(tenPounds, twoPounds))
        }
    }

    @Nested
    @DisplayName("Max")
    inner class MaxTest {

        @Test
        fun `check max`() {
            assertEquals(tenPounds, Money.max(tenPounds, twoPounds))
        }
    }
}