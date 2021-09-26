package uk.co.alexbroadbent.money

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale
import kotlin.math.pow
import kotlin.math.roundToLong

/**
 * @param amount amount of money in the smallest decimal, eg. (200 = £2.00) or (100 = ￥100)
 * @param currency origin of money for the given amount
 */
class Money private constructor(
    val amount: Long = 0,
    val currency: Currency = Currency.getInstance(Locale.getDefault())
) : Comparable<Money> {

    operator fun plus(other: Money) = arithmeticOperation(other) { amount.plus(it) }

    /**
     * Produces a new Money object with the given amount added on, the given amount is converted into minor units for
     * the currency of this Money object.
     *
     * @param other amount to add in minor units, for example 500 = £5.00
     */
    operator fun plus(other: Int) = Money(amount = amount.plus(other), currency = currency)

    /**
     * Produces a new Money object with the given amount added on, the given amount is converted into minor units for
     * the currency of this Money object.
     *
     * @param other amount to add in minor units, for example 10 = £0.10p
     */
    operator fun plus(other: Long) = Money(amount = amount.plus(other), currency = currency)

    /**
     * Produces a new Money object of the given Money object subtracted from this Money object's amount.
     *
     * @param other Money object to subtract
     * @throws IllegalArgumentException if the given Money object has a different currency
     */
    operator fun minus(other: Money) = arithmeticOperation(other) { amount.minus(it) }

    /**
     * Produces a new Money object with the given amount subtracted from this Money's amount.
     *
     * @param other amount to subtract in minor units, for example 1000 = £10.00
     */
    operator fun minus(other: Int) = Money(amount = amount.minus(other), currency = currency)

    /**
     * Produces a new Money object with the given amount subtracted from this Money's amount.
     *
     * @param other amount to subtract in minor units, for example 1000 = £10.00
     */
    operator fun minus(other: Long) = Money(amount = amount.minus(other), currency = currency)

    operator fun div(other: Int) = Money(amount = amount.toDouble().div(other).roundToLong(), currency)
    operator fun div(other: Long) = Money(amount = amount.toDouble().div(other).roundToLong(), currency)

    override fun compareTo(other: Money) = amount.compareTo(other.amount)

    fun percentage(percentage: Int) = times(percentage).div(100)
    fun percentage(percentage: Long) = times(percentage).div(100)

    fun isPositive() = amount >= 0
    fun isNegative() = amount < 0

    private fun arithmeticOperation(other: Money, op: (Long) -> Long) =
        other.takeIf { it.currency == currency }?.let { Money(amount = op(it.amount), currency) }
            ?: throw IllegalArgumentException("Cannot perform arithmetic on Money instances with different currencies")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Money
        if (amount != other.amount) return false
        if (currency != other.currency) return false
        return true
    }

    override fun hashCode(): Int = 31 * amount.hashCode() + currency.hashCode()

    override fun toString(): String = toString(Locale.getDefault())
    fun toString(locale: Locale): String = NumberFormat.getCurrencyInstance(locale)
        .apply {
            currency = this@Money.currency
            maximumFractionDigits = this@Money.currency.defaultFractionDigits
        }
        .format(toMajorAmount())

    private fun toMajorAmount() = amount.toDouble().div(10.toDouble().pow(currency.defaultFractionDigits.toDouble()))

    companion object {
        fun min(a: Money, b: Money): Money = if (a.amount <= b.amount) a else b
        fun max(a: Money, b: Money): Money = if (a.amount >= b.amount) a else b

        private fun Long.majorToMinor(currency: Currency) = times(currency.toFactor()).toLong()
        private fun Int.majorToMinor(currency: Currency) = times(currency.toFactor()).toLong()
        private fun Double.majorToMinor(currency: Currency) = times(currency.toFactor()).toLong()

        private fun Currency.toFactor() = 10.0.pow(defaultFractionDigits.toDouble())

        fun fromMinor(
            amount: Int,
            currency: Currency = Currency.getInstance(Locale.getDefault())
        ) = Money(amount.toLong(), currency)

        fun fromMinor(
            amount: Long,
            currency: Currency = Currency.getInstance(Locale.getDefault())
        ) = Money(amount, currency)

        fun fromMajor(
            amount: Int,
            currency: Currency = Currency.getInstance(Locale.getDefault())
        ) = Money(amount.majorToMinor(currency), currency)

        fun fromMajor(
            amount: Long,
            currency: Currency = Currency.getInstance(Locale.getDefault())
        ) = Money(amount.majorToMinor(currency), currency)

        fun fromMajor(
            amount: Double,
            currency: Currency = Currency.getInstance(Locale.getDefault())
        ) = Money(amount.majorToMinor(currency), currency)
    }
}
