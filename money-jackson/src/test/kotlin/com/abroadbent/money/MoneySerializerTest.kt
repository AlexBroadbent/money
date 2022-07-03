package com.abroadbent.money

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.Currency

class MoneySerializerTest : BaseTest() {

    @Test
    fun `serializes money object`() {
        val money = Money.fromMajor(5, Currency.getInstance("GBP"))
        assertEquals("""{"amount":500,"currency":"GBP"}""", mapper.writeValueAsString(money))
    }
}
