package com.abroadbent.money

import com.google.gson.GsonBuilder

fun GsonBuilder.registerMoneyModule(): GsonBuilder {
    registerTypeAdapter(Money::class.java, MoneyTypeAdapter)
    return this
}
