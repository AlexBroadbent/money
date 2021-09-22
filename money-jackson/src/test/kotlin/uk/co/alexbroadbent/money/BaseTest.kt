package uk.co.alexbroadbent.money

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

abstract class BaseTest {

    val mapper: ObjectMapper = ObjectMapper()
        .registerKotlinModule()
        .registerModule(MoneyModule())
}