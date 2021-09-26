package uk.co.alexbroadbent.money

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.BeanDescription
import com.fasterxml.jackson.databind.DeserializationConfig
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.Deserializers
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import java.util.Currency

object MoneyDeserializer : StdDeserializer<Money>(Money::class.java) {

    override fun deserialize(parser: JsonParser, ctxt: DeserializationContext): Money {
        val node: JsonNode = parser.codec.readTree(parser)

        val amount = node.takeIf { it.has("amount") }?.path("amount")?.asLong()
        val currency = node.takeIf { it.has("currency") }?.path("currency")?.asText()

        return when (amount == null || currency == null) {
            true -> throw IllegalStateException("Money class requires an amount and currency field present")
            else -> Money.fromMinor(amount, Currency.getInstance(currency))
        }
    }
}

internal class MoneyDeserializers : Deserializers.Base() {
    override fun findBeanDeserializer(
        type: JavaType,
        config: DeserializationConfig?,
        beanDesc: BeanDescription?
    ): JsonDeserializer<*>? = when (type.rawClass) {
        Money::class.java -> MoneyDeserializer
        else -> null
    }
}
