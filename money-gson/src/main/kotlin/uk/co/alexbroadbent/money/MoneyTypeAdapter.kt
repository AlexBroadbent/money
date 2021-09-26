package uk.co.alexbroadbent.money

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.util.Currency

object MoneyTypeAdapter : JsonSerializer<Money>, JsonDeserializer<Money> {

    override fun serialize(src: Money, typeOfSrc: Type?, context: JsonSerializationContext?) = JsonObject().apply {
        addProperty("amount", src.amount)
        addProperty("currency", src.currency.currencyCode)
    }

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Money {
        if (json is JsonObject) {
            return Money.fromMinor(
                json.get("amount").asLong,
                Currency.getInstance(json.get("currency").asString)
            )
        }
        throw IllegalStateException("Money must be an object type")
    }
}
