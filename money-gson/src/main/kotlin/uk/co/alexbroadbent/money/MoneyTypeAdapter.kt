package uk.co.alexbroadbent.money

import com.google.gson.*
import java.lang.reflect.Type
import java.util.*

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