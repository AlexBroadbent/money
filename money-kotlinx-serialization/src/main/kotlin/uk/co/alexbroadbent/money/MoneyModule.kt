package uk.co.alexbroadbent.money

import kotlinx.serialization.modules.SerializersModule

val moneyModule = SerializersModule {
    contextual(Money::class, MoneySerializer)
}