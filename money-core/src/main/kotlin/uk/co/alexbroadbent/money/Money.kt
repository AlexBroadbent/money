package uk.co.alexbroadbent.money

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale
import kotlin.math.pow
import kotlin.math.roundToLong

class Money private constructor(
    val amount: Long = 0,
    val currency: Currency = Currency.getInstance(Locale.getDefault())
) : Comparable<Money> {

    /**
     * Produces a new Money object with the sum of the amounts for both Money objects.
     *
     * @param other Money object to add
     * @throws IllegalArgumentException if the given Money object has a different currency
     * @sample uk.co.alexbroadbent.money.MoneyArithmeticTest.PlusOperatorTest
     */
    operator fun plus(other: Money) = arithmeticOperation(other) { amount.plus(it) }

    /**
     * Produces a new Money object with the given amount added on, the given amount is converted into minor units for
     * the currency of this Money object.
     *
     * @param other amount to add in minor units, for example 500 = £5.00
     * @sample uk.co.alexbroadbent.money.MoneyArithmeticTest.PlusOperatorTest
     */
    operator fun plus(other: Int) = Money(amount = amount.plus(other), currency = currency)

    /**
     * Produces a new Money object with the given amount added on, the given amount is converted into minor units for
     * the currency of this Money object.
     *
     * @param other amount to add in minor units, for example 10 = £0.10p
     * @sample uk.co.alexbroadbent.money.MoneyArithmeticTest.PlusOperatorTest
     */
    operator fun plus(other: Long) = Money(amount = amount.plus(other), currency = currency)

    /**
     * Produces a new Money object of the given Money object subtracted from this Money object's amount.
     *
     * @param other Money object to subtract
     * @throws IllegalArgumentException if the given Money object has a different currency
     * @sample uk.co.alexbroadbent.money.MoneyArithmeticTest.MinusOperatorTest
     */
    operator fun minus(other: Money) = arithmeticOperation(other) { amount.minus(it) }

    /**
     * Produces a new Money object with the given amount subtracted from this Money's amount.
     *
     * @param other amount to subtract in minor units, for example 1000 = £10.00
     * @sample uk.co.alexbroadbent.money.MoneyArithmeticTest.MinusOperatorTest
     */
    operator fun minus(other: Int) = Money(amount = amount.minus(other), currency = currency)

    /**
     * Produces a new Money object with the given amount subtracted from this Money's amount.
     *
     * @param other amount to subtract in minor units, for example 1000 = £10.00
     * @sample uk.co.alexbroadbent.money.MoneyArithmeticTest.MinusOperatorTest
     */
    operator fun minus(other: Long) = Money(amount = amount.minus(other), currency = currency)

    /**
     * Produces a new Money object with this Money's amount multiplied by the given factor.
     *
     * @param factor amount to multiply the amount by
     * @sample uk.co.alexbroadbent.money.MoneyArithmeticTest.TimesOperatorTest
     */
    operator fun times(factor: Int) = Money(amount = amount.times(factor), currency = currency)

    /**
     * Produces a new Money object with this Money's amount multiplied by the given factor.
     *
     * @param factor amount to multiply the amount by
     * @sample uk.co.alexbroadbent.money.MoneyArithmeticTest.TimesOperatorTest
     */
    operator fun times(factor: Long) = Money(amount = amount.times(factor), currency = currency)

    /**
     * Produces a new Money object with this Money's amount divided by the given factor.
     *
     * @param factor amount to divide the amount by
     * @sample uk.co.alexbroadbent.money.MoneyArithmeticTest.DivideOperatorTest
     */
    operator fun div(factor: Int) = Money(amount = amount.toDouble().div(factor).roundToLong(), currency = currency)

    /**
     * Produces a new Money object with this Money's amount divided by the given factor.
     *
     * @param factor amount to divide the amount by
     * @sample uk.co.alexbroadbent.money.MoneyArithmeticTest.DivideOperatorTest
     */
    operator fun div(factor: Long) = Money(amount = amount.toDouble().div(factor).roundToLong(), currency = currency)

    /**
     * Compares this object with the specified object for order. Returns zero if this object is equal to the specified
     * other object, a negative number if it's less than other, or a positive number if it's greater than other.
     *
     * Note: this will only compare Money objects by amount and will not take the currency into consideration.
     * Therefore, this operation is only recommended, but not enforced, on Money objects with the same currency to avoid
     * any misleading result.
     *
     * @param other given Money object to compare to
     * @sample uk.co.alexbroadbent.money.MoneyComparableTest
     */
    override fun compareTo(other: Money) = amount.compareTo(other.amount)

    /**
     * Produces a new Money object which takes the given percentile and returns the amount as a percentage of this
     * Money objects' amount.
     *
     * @param percentile the percentile to calculate, for example 10 = 10%
     * @sample uk.co.alexbroadbent.money.MoneyExtraFunctionsTest.PercentageTest
     */
    fun percentage(percentile: Int) = times(percentile).div(100)

    /**
     * Produces a new Money object which takes the given percentile and returns the amount as a percentage of this
     * Money objects' amount.
     *
     * @param percentile the percentile to calculate, for example 10 = 10%
     * @sample uk.co.alexbroadbent.money.MoneyExtraFunctionsTest.PercentageTest
     */
    fun percentage(percentile: Long) = times(percentile).div(100)

    /**
     * Returns true if the amount of this Money object is greater than or equal to zero.
     *
     * @sample uk.co.alexbroadbent.money.MoneyExtraFunctionsTest.IsPositiveTest
     */
    fun isPositive() = amount >= 0

    /**
     * Returns true if the amount of this Money object is less than zero.
     *
     * @sample uk.co.alexbroadbent.money.MoneyExtraFunctionsTest.IsNegativeTest
     */
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

    /**
     * Formats the Money object as a Currency object using the given locale.
     *
     * @param locale the locale to use for currency styling
     * @sample uk.co.alexbroadbent.money.MoneyToStringTest
     */
    fun toString(locale: Locale): String = NumberFormat.getCurrencyInstance(locale)
        .apply {
            currency = this@Money.currency
            maximumFractionDigits = this@Money.currency.defaultFractionDigits
        }
        .format(toMajorAmount())

    private fun toMajorAmount() = amount.toDouble().div(10.toDouble().pow(currency.defaultFractionDigits.toDouble()))

    companion object {
        /**
         * Returns the smallest of the two given Money objects by their amount.
         *
         * Note: this will only compare Money objects by amount and will not take the currency into consideration.
         * Therefore, this operation is only recommended, but not enforced, on Money objects with the same currency
         * to avoid any misleading result.
         * @sample uk.co.alexbroadbent.money.MoneyUtilTest.MinTest
         */
        fun min(a: Money, b: Money): Money = if (a.amount <= b.amount) a else b

        /**
         * Returns the largest of the two given Money objects by their amount.
         *
         * Note: this will only compare Money objects by amount and will not take the currency into consideration.
         * Therefore, this operation is only recommended, but not enforced, on Money objects with the same currency
         * to avoid any misleading result.
         *
         * @sample uk.co.alexbroadbent.money.MoneyUtilTest.MaxTest
         */
        fun max(a: Money, b: Money): Money = if (a.amount >= b.amount) a else b

        private fun Long.majorToMinor(currency: Currency) = times(currency.toFactor()).toLong()
        private fun Int.majorToMinor(currency: Currency) = times(currency.toFactor()).toLong()
        private fun Double.majorToMinor(currency: Currency) = times(currency.toFactor()).toLong()

        private fun Currency.toFactor() = 10.0.pow(defaultFractionDigits.toDouble())

        /**
         * Construct a new Money object by taking the given amount as the smallest denomination of the given currency.
         *
         * @param amount amount of money in the smallest denomination, for example (200 = £2.00) or (100 = ￥100)
         * @param currency origin of money for the given amount
         */
        fun fromMinor(
            amount: Int,
            currency: Currency = Currency.getInstance(Locale.getDefault())
        ) = Money(amount.toLong(), currency)

        /**
         * Construct a new Money object by taking the given amount as the smallest denomination of the given currency.
         *
         * @param amount amount of money in the smallest denomination, for example (200 = £2.00) or (100 = ￥100)
         * @param currency origin of money for the given amount
         */
        fun fromMinor(
            amount: Long,
            currency: Currency = Currency.getInstance(Locale.getDefault())
        ) = Money(amount, currency)

        /**
         * Construct a new Money object by taking the given amount as the largest denomination of the given currency.
         *
         * @param amount amount of money in the largest denomination, for example (2 = £2.00) or (100 = ￥100)
         * @param currency origin of money for the given amount
         */
        fun fromMajor(
            amount: Int,
            currency: Currency = Currency.getInstance(Locale.getDefault())
        ) = Money(amount.majorToMinor(currency), currency)

        /**
         * Construct a new Money object by taking the given amount as the largest denomination of the given currency.
         *
         * @param amount amount of money in the largest denomination, for example (2 = $2.00) or (100 = ￥100)
         * @param currency origin of money for the given amount
         */
        fun fromMajor(
            amount: Long,
            currency: Currency = Currency.getInstance(Locale.getDefault())
        ) = Money(amount.majorToMinor(currency), currency)

        /**
         * Construct a new Money object by taking the given amount as the largest denomination of the given currency.
         *
         * @param amount amount of money in the largest denomination, for example (2 = 2.00 €) or (100 = ￥100)
         * @param currency origin of money for the given amount
         */
        fun fromMajor(
            amount: Double,
            currency: Currency = Currency.getInstance(Locale.getDefault())
        ) = Money(amount.majorToMinor(currency), currency)
    }
}
