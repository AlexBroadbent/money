package uk.co.alexbroadbent.money

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class MoneyEqualsTest : BaseTest() {

    @Test
    fun `check equality on amount and currency`() {
        val twoPoundsTwo = Money.fromMajor(2, gbp)
        assertEquals(twoPoundsTwo, twoPounds)
    }

    @Test
    fun `check non-equality on amount`() {
        assertNotEquals(tenPounds, twoPounds)
    }

    @Test
    fun `check non-equality on currency`() {
        val twoEuros = Money.fromMajor(2, eur)
        assertNotEquals(twoEuros, twoPounds)
    }

    @Test
    fun `check hash code`() {
        assertEquals(twoPounds.hashCode(), twoPounds.hashCode())
    }
}