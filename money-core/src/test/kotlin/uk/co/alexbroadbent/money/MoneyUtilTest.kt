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

        @Test
        fun `check first argument returned when amounts are equal`() {
            val twoPounds2 = Money.fromMajor(2, gbp)
            assertEquals(twoPounds, Money.min(tenPounds, twoPounds2))
        }
    }

    @Nested
    @DisplayName("Max")
    inner class MaxTest {

        @Test
        fun `check max`() {
            assertEquals(tenPounds, Money.max(tenPounds, twoPounds))
        }

        @Test
        fun `check first argument returned when amounts are equal`() {
            val tenPounds2 = Money.fromMajor(10, gbp)
            assertEquals(tenPounds, Money.max(tenPounds, tenPounds2))
        }
    }
}