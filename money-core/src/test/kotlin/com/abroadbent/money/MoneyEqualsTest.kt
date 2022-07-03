package com.abroadbent.money

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import java.util.Currency

class MoneyEqualsTest : BaseTest() {

    @Test
    fun `check equality on amount and currency`() {
        val twoPoundsTwo = Money.fromMajor(2, gbp)
        assertEquals(twoPoundsTwo, twoPounds)
    }

    @Test
    fun `check equality on two different currency objects`() {
        val money1 = Money.fromMajor(2, Currency.getInstance("GBP"))
        val money2 = Money.fromMajor(2, Currency.getInstance("GBP"))
        assertEquals(money1, money2)
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
