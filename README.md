## Money

The aim of this library is to provide a simple utility class to provide simple operations that revolve around money.

---

### Features:

* Simple class which stores the amount in the lowest denomination to avoid using decimal numbers
* Printable using a given `Locale` to format the numbers in the given area, eg. 3.000,00 € or £3,000.00
* JSON seralization and deserialization modules provided for Jackson, Gson and KotlinX Serialization

---

### Usage Documentation

For the examples, we'll use the shorthand of declaring some examples up front:

```kotlin
val GBP = Currency.getInstance("GBP")
val EURO = Currency.getInstance("EUR")
val YEN = Currency.getInstance("JPY")

val fivePounds = Money.fromMajor(5, GBP)            // £5.00
val tenPence = Money.fromMinor(10, GBP)             // £0.10p 
val twoEuros = Money.fromMajor(2, EURO)             // 0.02 €
val eightCent = Money.fromMinor(8, EURO)            // 8.00 €
val tenThousandYen = Money.fromMajor(10_000, YEN)   // ￥10,000
```

| Method | Example | Purpose |
| --- | --- | --- |
| Money.fromMinor(Int, currency) | `Money.fromMinor(50, GBP)` | Constructor using the lowest denomination, this will take the given amount in it's given form.| Money.fromMinor(amount, currency) | Money.fromMinor(50, Currency.getInstance("GBP") | Constructor using the lowest denomination, this will take the given amount in it's given form. In the example, this would represent £0.50p. |
| Money.fromMinor(Long, currency) | `Money.fromMinor(50, GBP)` | Constructor using the lowest denomination, this will take the given amount in it's given form.| Money.fromMinor(amount, currency) | Money.fromMinor(50, Currency.getInstance("GBP") | Constructor using the lowest denomination, this will take the given amount in it's given form. In the example, this would represent £0.50p. |
| Money.fromMajor(Int, currency) | `Money.fromMajor(50, EURO)` | Constructor using the lowest denomination, this will take the given amount in it's given form.| Money.fromMinor(amount, currency) | Money.fromMinor(50, Currency.getInstance("GBP") | Constructor using the lowest denomination, this will take the given amount and transorm it using the default number of decimal places for the given currency. In the example, this would represent £50.00 |
| Money.fromMajor(Long, currency) | `Money.fromMajor(50, YEN)` | Constructor using the lowest denomination, this will take the given amount in it's given form.| Money.fromMinor(amount, currency) | Money.fromMinor(50, Currency.getInstance("GBP") | Constructor using the lowest denomination, this will take the given amount and transorm it using the default number of decimal places for the given currency. In the example, this would represent £50.00 |
| Money.fromMajor(Double, currency) | `Money.fromMinor(50, EURO)` | Constructor using the lowest denomination, this will take the given amount in it's given form.| Money.fromMinor(amount, currency) | Money.fromMinor(50, Currency.getInstance("GBP") | Constructor using the lowest denomination, this will take the given amount in it's given form. In the example, this would represent £0.50p. |
| + (Money) | `fivePounds + tenPence` | Addition operator for two  | 
| + (Int) | `eightCent + 200` | Addition operator which takes an Integer and converts it into the same currency as the given Money object in minor units | 
| + (Long) | `twoEuros + 500L` | Addition operator which takes a Long and converts it into the same currency as the given Money object in minor units | 
| - (Money) | `fivePounds - tenPence` | Subtraction operator | 
| - (Int) | `tenPence - 100` | Subtraction operator which takes an Integer and converts it into the same currency as the given Money object in minor units  | 
| - (Long) | `fivePounds - 650L` | Subtraction operator which takes a Long and converts it into the same currency as the given Money object in minor units  | 
| * (Int) | `twoEuros * 4` | Multiply a monetary value by a given factor | 
| * (Long) | `twoEuros * 10L` | Multiply a monetary value by a given factor | 
| / (Int) | `tenThousandYen / 2` | Divide a monetary value by a given factor | 
| / (Long) | `tenThousandYen / 5L` | Divide a monetary value by a given factor | 
| compareTo | `fivePounds < tenPence` | Comparator that allows `<`, `>` and sorting methods as part of the `Comparable` interface |
| isPositive() | `eightCent.isPositive()` | Check positivity (0 or greater) on the amount of the money object |
| isNegative() | `fivePounds.isNegative()` | Check negativity (less than 0) on the amount of the money object |
| percentage(Int) | `tenThousandYen.percentage(50)` | Returns a new Money object as the given percentage of a factor |
| percentage(Long) | `fivePounds.percentage(10L)` | Returns a new Money object as the given percentage of a factor |
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
    <groupId>uk.co.alexbroadbent</groupId>
    <artifactId>money-core</artifactId>
    <version>0.1.0</version>
</dependency>
```

##### Gradle

Groovy: 
```implementation 'uk.co.alexbroadbent:money-core:0.1.0'```

Kotlin: 
```implementation("uk.co.alexbroadbent:money-core:0.1.0")```

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
    <groupId>uk.co.alexbroadbent</groupId>
    <artifactId>money-gson</artifactId>
    <version>0.1.0</version>
</dependency>
```

##### Gradle

Groovy:
```implementation 'uk.co.alexbroadbent:money-gson:0.1.0'```

Kotlin:
```implementation("uk.co.alexbroadbent:money-gson:0.1.0")```

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
    <groupId>uk.co.alexbroadbent</groupId>
    <artifactId>money-jackson</artifactId>
    <version>0.1.0</version>
</dependency>
```

##### Gradle

Groovy:
```implementation 'uk.co.alexbroadbent:money-jackson:0.1.0'```

Kotlin:
```implementation("uk.co.alexbroadbent:money-jackson:0.1.0")```


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
    <groupId>uk.co.alexbroadbent</groupId>
    <artifactId>money-kotlinx-serialization</artifactId>
    <version>0.1.0</version>
</dependency>
```

##### Gradle

Groovy:
```implementation 'uk.co.alexbroadbent:money-kotlinx-serialization:0.1.0'```

Kotlin:
```implementation("uk.co.alexbroadbent:money-kotlinx-serialization:0.1.0")```

##### Usage

The serializer and deserializer has been put into the `moneyModule` which can set within the `Json { }` construction:

```kotlin
private val json = Json { serializersModule = moneyModule }
```
