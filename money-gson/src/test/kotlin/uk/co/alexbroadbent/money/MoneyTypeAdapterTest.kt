package uk.co.alexbroadbent.money

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.Currency

class MoneyTypeAdapterTest {

    val gson: Gson = GsonBuilder().registerMoneyModule().create()

    @Nested
    @DisplayName("Serializer")
    inner class MoneySerializerTest {

        @Test
        fun `deserializes money object`() {
            val money = Money.fromMajor(5, Currency.getInstance("GBP"))
            assertEquals("""{"amount":500,"currency":"GBP"}""", gson.toJson(money))
        }
    }

    @Nested
    @DisplayName("Deserializer")
    inner class MoneyDeserializerTest {
        @Test
        fun `serializes money object`() {
            val json = """{"amount": 500, "currency": "GBP"}"""
            val expected = Money.fromMajor(5, Currency.getInstance("GBP"))
            val actual = gson.fromJson(json, Money::class.java)

            assertEquals(expected, actual)
        }
    }
}
