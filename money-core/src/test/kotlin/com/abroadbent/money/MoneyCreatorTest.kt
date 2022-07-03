package com.abroadbent.money

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MoneyCreatorTest : BaseTest() {

    @Test
    fun `test creation from minor long`() {
        val money = Money.fromMinor(100L, gbp)
        Assertions.assertEquals("£1.00", money.toString())
    }

    @Test
    fun `test creation from minor int`() {
        val money = Money.fromMinor(200, gbp)
        Assertions.assertEquals("£2.00", money.toString())
    }

    @Test
    fun `test creation from major double`() {
        val money = Money.fromMajor(2.25, gbp)
        Assertions.assertEquals("£2.25", money.toString())
    }

    @Test
    fun `test creation from major int`() {
        val money = Money.fromMajor(3, gbp)
        Assertions.assertEquals("£3.00", money.toString())
    }

    @Test
    fun `test creation from major long`() {
        val money = Money.fromMajor(4L, gbp)
        Assertions.assertEquals("£4.00", money.toString())
    }

    @Test
    fun `test creation from minor long with default currency`() {
        val money = Money.fromMinor(100L)
        Assertions.assertEquals("£1.00", money.toString())
    }

    @Test
    fun `test creation from minor int with default currency`() {
        val money = Money.fromMinor(200)
        Assertions.assertEquals("£2.00", money.toString())
    }

    @Test
    fun `test creation from major double with default currency`() {
        val money = Money.fromMajor(2.25)
        Assertions.assertEquals("£2.25", money.toString())
    }

    @Test
    fun `test creation from major int with default currency`() {
        val money = Money.fromMajor(3)
        Assertions.assertEquals("£3.00", money.toString())
    }

    @Test
    fun `test creation from major long with default currency`() {
        val money = Money.fromMajor(4L)
        Assertions.assertEquals("£4.00", money.toString())
    }
}
