package com.abroadbent.money

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.Currency
import java.util.Locale

class MoneyArithmeticTest : BaseTest() {

    @Nested
    @DisplayName("Plus")
    inner class PlusOperatorTest {
        @Test
        fun `plus another Money object`() {
            assertEquals(tenPounds, fivePounds + fivePounds)
        }

        @Test
        fun `plus int`() {
            assertEquals(fivePounds, twoPounds + 300)
        }

        @Test
        fun `plus long`() {
            assertEquals(onePound, fiftyPence + 50L)
        }

        @Test
        fun `plus fails on differing currencies`() {
            val fiveEuros = Money.fromMajor(5, eur)
            assertThrows<IllegalArgumentException> { fivePounds + fiveEuros }
        }
    }

    @Nested
    @DisplayName("Minus")
    inner class MinusOperatorTest {
        @Test
        fun `minus another Money object`() {
            assertEquals(fivePounds, tenPounds - fivePounds)
        }

        @Test
        fun `minus int`() {
            assertEquals(twoPounds, fivePounds - 300)
        }

        @Test
        fun `minus long`() {
            assertEquals(twoPounds, tenPounds - 800L)
        }

        @Test
        fun `minus fails on differing currencies`() {
            val threeYen = Money.fromMajor(3, Currency.getInstance(Locale.JAPAN))
            assertThrows<IllegalArgumentException> { fivePounds - threeYen }
        }
    }

    @Nested
    @DisplayName("Times")
    inner class TimesOperatorTest {
        @Test
        fun `times by int`() {
            assertEquals(tenPounds, fivePounds * 2)
        }

        @Test
        fun `times by long`() {
            assertEquals(twoPounds, fiftyPence * 4L)
        }
    }

    @Nested
    @DisplayName("Divide")
    inner class DivideOperatorTest {
        @Test
        fun `divide by integer`() {
            assertEquals(fiftyPence, tenPounds / 20)
        }

        @Test
        fun `divide by long`() {
            assertEquals(onePound, fivePounds / 5L)
        }
    }
}
