package uk.co.alexbroadbent.money

import java.util.Currency

abstract class BaseTest {
    val gbp: Currency = Currency.getInstance("GBP")
    val eur: Currency = Currency.getInstance("EUR")

    // test fixtures
    val fiftyPence = 50.toMinorGBP()
    val onePound = 1.toMajorGBP()
    val twoPounds = 2.toMajorGBP()
    val fivePounds = 5.toMajorGBP()
    val tenPounds = 10.toMajorGBP()

    fun Long.toMajorGBP() = Money.fromMajor(this, gbp)
    fun Long.toMinorGBP() = Money.fromMinor(this, gbp)
    fun Int.toMajorGBP() = Money.fromMajor(this, gbp)
    fun Int.toMinorGBP() = Money.fromMinor(this, gbp)
    fun Double.toMajorGBP() = Money.fromMajor(this, gbp)
}
