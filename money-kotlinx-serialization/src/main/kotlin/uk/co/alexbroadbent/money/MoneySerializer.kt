package uk.co.alexbroadbent.money

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import java.util.*

object MoneySerializer : KSerializer<Money> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Money") {
        element<Long>("amount", isOptional = false)
        element<String>("currency", isOptional = false)
    }

    override fun serialize(encoder: Encoder, value: Money) {
        encoder.encodeStructure(descriptor) {
            encodeLongElement(descriptor, 0, value.amount)
            encodeStringElement(descriptor, 1, value.currency.currencyCode)
        }
    }

    override fun deserialize(decoder: Decoder): Money = decoder.decodeStructure(descriptor) {
        var amount: Long? = null
        var currency: String? = null

        while (true) {
            when (val index = decodeElementIndex(descriptor)) {
                0 -> amount = decodeLongElement(descriptor, 0)
                1 -> currency = decodeStringElement(descriptor, 1)
                CompositeDecoder.DECODE_DONE -> break
                else -> error("Unexpected index: $index")
            }
        }
        require(amount != null && currency != null)
        Money.fromMinor(amount, Currency.getInstance(currency))
    }
}
