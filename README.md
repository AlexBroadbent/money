## Money

The aim of this library is to provide a simple utility class to provide simple operations that revolve around money.

[![Build](https://github.com/AlexBroadbent/money/actions/workflows/build.yml/badge.svg)](https://github.com/AlexBroadbent/money/actions/workflows/build.yml)
![Maven Central](https://img.shields.io/maven-central/v/com.abroadbent/money-core)

---

### Features:

* Simple class which stores the amount in the lowest denomination to avoid using decimal numbers
* Printable using a given `Locale` to format the numbers in the given area, eg. £3.000,00 or £3,000.00
* JSON serialization and deserialization modules provided for Jackson, Gson and KotlinX Serialization

---

### Usage Documentation

For the examples, we'll use the shorthand of declaring some examples up front:

```kotlin
val GBP = Currency.getInstance("GBP")
val EURO = Currency.getInstance("EUR")
val YEN = Currency.getInstance("JPY")

val fivePounds = Money.fromMajor(5, GBP)            // £5.00
val tenPence = Money.fromMinor(10, GBP)             // £0.10
val twoEuros = Money.fromMajor(2, EURO)             // 2.00 €
val eightCent = Money.fromMinor(8, EURO)            // 0.08 €
val tenThousandYen = Money.fromMajor(10_000, YEN)   // ￥10,000
```

| Method | Example | Purpose |
| --- | --- | --- |
| Money.fromMinor(Int, currency) | `Money.fromMinor(50, GBP)` | Constructor using the lowest denomination, this will take the given amount as the total amount in the given currency. In the example this would produce a Money object of £0.50p | 
| Money.fromMinor(Long, currency) | `Money.fromMinor(250, GBP)` | Constructor using the lowest denomination, this will take the given amount as the total amount in the given currency. In the example this would produce a Money object of £2.50p |
| Money.fromMajor(Int, currency) | `Money.fromMajor(50, EURO)` | Constructor using the highest denomination, this will take the given amount in it's given form and factor in the number of decimal places required for the given currency. In the example this would produce a Money object of 50.00 € |
| Money.fromMajor(Long, currency) | `Money.fromMajor(5000, YEN)` | Constructor using the highest denomination, this will take the given amount in it's given form and factor in the number of decimal places required for the given currency. In the example this would produce a Money object of ￥5000 |
| Money.fromMajor(Double, currency) | `Money.fromMajor(200, EURO)` | Constructor using the lowest denomination, this will take the given amount in it's given form and factor in the number of decimal places required for the given currency. In the example this would produce a Money object of 200.00 € |
| + (Money) | `fivePounds + tenPence` | Addition operator for two Money objects. If the currency is not the same then an `IllegalArgumentException` will be thrown | 
| + (Int or Long) | `twoEuros + 500` | Addition operator which takes a Long and converts it into the same currency as the given Money object in minor units | 
| - (Money) | `fivePounds - tenPence` | Subtraction operator for two Money objects. If the currency is not the same then an `IllegalArgumentException` will be thrown |
| - (Int or Long) | `fivePounds - 650` | Subtraction operator which takes a Long and converts it into the same currency as the given Money object in minor units  | 
| * (Int or Long) | `twoEuros * 10` | Multiply a monetary value by a given factor | 
| / (Int or Long) | `tenThousandYen / 5L` | Divide a monetary value by a given factor | 
| compareTo(Money) | `fivePounds < tenPence` | Comparator that allows `<`, `>` and sorting methods by comparing this object with the given Money object for order.<br/><br/>NOTE: this will only compare Money objects by amount and will not take the currency into consideration. Therefore, this operation is only recommended, _but not enforced_, on Money objects with the same currency to avoid any misleading result. |
| isPositive() | `eightCent.isPositive()` | Check positivity (0 or greater) on the amount of the money object |
| isNegative() | `fivePounds.isNegative()` | Check negativity (less than 0) on the amount of the money object |
| percentage(Int or Long) | `fivePounds.percentage(10)` | Takes the given percentile and returns the amount as a percentage of this Money objects' amount. |
| toString() | `fivePounds.toString()` | Uses the default System locale to print out the money object |
| toString(Locale) | `twoEuros.toString(Locale.FRANCE)` | Uses the given locale to print out the money object including the symbol and number of decimal places for the locale |
| Money.min(Money, Money) | `Money.min(fivePence, tenPence)` | Returns the Money object of the smaller amount from two given money objects, if they are equal then the first argument is returned |
| Money.max(Money, Money) | `Money.max(twoEuros, eightCent)` | Returns the Money object of the larger amount from two given money objects, if they are equal then the first argument is returned |

---

### Dependencies

#### Money-Core

Using the core dependency:

##### Maven

```xml

<dependency>
    <groupId>com.abroadbent</groupId>
    <artifactId>money-core</artifactId>
    <version>0.3.0</version>
</dependency>
```

##### Gradle

Groovy: 
```implementation 'com.abroadbent:money-core:0.3.0'```

Kotlin: 
```implementation("com.abroadbent:money-core:0.3.0")```

##### Usage

The `Money` class can be used as documented above

```kotlin
fun Long.toGBP() = Money.fromMajor(this, Currency.getInstance("GBP"))
fun Money.print() = this.toString(Locale.UK)

val fivePounds = 5L.toGBP().also { it.print() }
```

---

#### Money-Gson

Bringing in the Gson serialization, which also brings in the `money-core` dependency

##### Maven

```xml

<dependency>
    <groupId>com.abroadbent</groupId>
    <artifactId>money-gson</artifactId>
    <version>0.3.0</version>
</dependency>
```

##### Gradle

Groovy:
```implementation 'com.abroadbent:money-gson:0.3.0'```

Kotlin:
```implementation("com.abroadbent:money-gson:0.3.0")```

##### Usage

A TypeAdapter has been created to deserialize and serialize `Money` objects, just register the module via:

```kotlin
val gson = GsonBuilder().registerMoneyModule().create()
```

---

#### Money-Jackson

Bringing in the Jackson serialization, which also brings in the `money-core` dependency

##### Maven

```xml

<dependency>
    <groupId>com.abroadbent</groupId>
    <artifactId>money-jackson</artifactId>
    <version>0.3.0</version>
</dependency>
```

##### Gradle

Groovy:
```implementation 'com.abroadbent:money-jackson:0.3.0'```

Kotlin:
```implementation("com.abroadbent:money-jackson:0.3.0")```


##### Usage

A TypeAdapter has been created to deserialize and serialize `Money` objects, just register the module via:

```kotlin
val mapper: ObjectMapper = ObjectMapper().registerKotlinModule().registerModule(MoneyModule())
```

---

#### Money-Kotlinx-Serialization

Bringing in the kotlinx-serialization, which also brings in the `money-core` dependency

##### Maven

```xml

<dependency>
    <groupId>com.abroadbent</groupId>
    <artifactId>money-kotlinx-serialization</artifactId>
    <version>0.3.0</version>
</dependency>
```

##### Gradle

Groovy:
```implementation 'com.abroadbent:money-kotlinx-serialization:0.3.0'```

Kotlin:
```implementation("com.abroadbent:money-kotlinx-serialization:0.3.0")```

##### Usage

The serializer and deserializer has been put into the `moneyModule` which can set within the `Json { }` construction:

```kotlin
private val json = Json { serializersModule = moneyModule }
```
