package uk.co.alexbroadbent.money

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.ser.Serializers
import com.fasterxml.jackson.databind.ser.std.StdSerializer

object MoneySerializer : StdSerializer<Money>(Money::class.java) {

    override fun serialize(value: Money, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeStartObject()
        gen.writeNumberField("amount", value.amount)
        gen.writeStringField("currency", value.currency.currencyCode)
        gen.writeEndObject()
    }
}

internal class MoneySerializers : Serializers.Base() {

    override fun findSerializer(
        config: SerializationConfig?,
        type: JavaType,
        beanDesc: BeanDescription?
    ): JsonSerializer<*>? = when {
        Money::class.java.isAssignableFrom(type.rawClass) -> MoneySerializer
        else -> null
    }
}