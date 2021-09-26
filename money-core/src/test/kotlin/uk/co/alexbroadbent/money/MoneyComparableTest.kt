package uk.co.alexbroadbent.money

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.Currency
import java.util.Locale

class MoneyComparableTest : BaseTest() {

    @Test
    fun `test greater than operator`() {
        assertTrue(tenPounds > fivePounds)
    }

    @Test
    fun `test less than operator`() {
        assertTrue(fiftyPence < onePound)
    }

    @Test
    fun `test ordering with differing amounts`() {
        val array = listOf(tenPounds, fivePounds, twoPounds, onePound, fiftyPence)
        assertEquals(array.reversed(), array.sorted())
    }

    @Test
    fun `test ordering with differing currencies still uses amount`() {
        val array = listOf(
            tenPounds,
            Money.fromMajor(7, Currency.getInstance(Locale.FRANCE)),
            Money.fromMajor(3, Currency.getInstance(Locale.ITALY)),
            Money.fromMajor(1, Currency.getInstance(Locale.CANADA)),
            fiftyPence
        )
        assertEquals(array.reversed(), array.sorted())
    }
}
