package uk.co.alexbroadbent.money

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.util.*

class MoneySerializerTest : BaseTest() {

    @Test
    fun `serializes money object`() {
        val money = Money.fromMajor(5, Currency.getInstance("GBP"))
        assertEquals("""{"amount":500,"currency":"GBP"}""", mapper.writeValueAsString(money))
    }
}