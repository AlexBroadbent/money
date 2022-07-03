package com.abroadbent.money

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.Currency

class MoneySerializerTest {

    private val jsonSerializer = Json { serializersModule = moneyModule }

    @Nested
    @DisplayName("Serializer")
    inner class MoneySerializerTest {

        @Test
        fun `deserializes money object`() {
            val money = Money.fromMajor(5, Currency.getInstance("GBP"))
            Assertions.assertEquals("""{"amount":500,"currency":"GBP"}""", jsonSerializer.encodeToString(money))
        }
    }

    @Nested
    @DisplayName("Deserializer")
    inner class MoneyDeserializerTest {
        @Test
        fun `serializes money object`() {
            val json = """{"amount": 500, "currency": "GBP"}"""
            val expected = Money.fromMajor(5, Currency.getInstance("GBP"))
            val actual = jsonSerializer.decodeFromString<Money>(json)

            Assertions.assertEquals(expected, actual)
        }
    }
}
