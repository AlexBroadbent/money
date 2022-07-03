package com.abroadbent.money

import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.Currency

class MoneyDeserializerTest : BaseTest() {

    @Test
    fun `deserializes money object`() {
        val json = """{"amount": 500, "currency": "GBP"}"""
        val expected = Money.fromMajor(5, Currency.getInstance("GBP"))
        val actual = mapper.readValue<Money>(json)

        assertEquals(expected, actual)
    }
}
