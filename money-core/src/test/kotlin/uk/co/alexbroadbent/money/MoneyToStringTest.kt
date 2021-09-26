package uk.co.alexbroadbent.money

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.Currency
import java.util.Locale

class MoneyToStringTest : BaseTest() {

    @Test
    fun `test default toString`() {
        assertEquals("£10.00", tenPounds.toString())
    }

    @Test
    fun `test UK toString`() {
        assertEquals("£5.00", fivePounds.toString(Locale.UK))
    }

    @Test
    fun `test EU toString of euros`() {
        val twoEuros = Money.fromMajor(2, eur)
        assertEquals("2,00 €", twoEuros.toString(Locale.GERMANY))
    }

    @Test
    fun `test UK toString of euros`() {
        val fiveEuros = Money.fromMajor(5, eur)
        assertEquals("€5.00", fiveEuros.toString(Locale.UK))
    }

    @Test
    fun `test Yen toString`() {
        val tenThousandYen = Money.fromMajor(10_000, Currency.getInstance(Locale.JAPAN))
        assertEquals("￥10,000", tenThousandYen.toString(Locale.JAPAN))
    }
}
