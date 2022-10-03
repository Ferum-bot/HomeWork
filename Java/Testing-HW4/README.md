# HomeWork 4

Source code [here](./src/main/kotlin/Account.kt).

Test code [here](./src/test/kotlin/AccountTest.kt).

[Open](./coverage/index.html) coverage.

## Requirements:
* JDK 17
* Kotlin
* IntelliJ IDEA

## Changes:
### First Change:
#### Old:
```kotlin
fun setMaxCredit(mc: Int): Boolean {
        maxCredit = if (mc < -bound || mc > bound) return false else -mc
        return true
}
```
#### New:
```kotlin
fun setMaxCredit(mc: Int): Boolean {
    if (!isBlocked) {
        return false
    }
    maxCredit = if (mc < -bound || mc > bound) return false else -mc
    return true
}
```
### Second change:
#### Old:
```kotlin
fun deposit(sum: Int): Boolean {
        return if (isBlocked) false else if (sum < 0 || sum > bound) false else {
            balance += sum
            true
        }
}
```
#### New:
```kotlin
fun deposit(sum: Int): Boolean {
        if (abs(balance + sum) > bound) {
           return false 
        }
        return if (isBlocked) false else if (sum < 0 || sum > bound) false else {
            balance += sum
            true
        }
}
```

### Third change:
#### Old:
```kotlin
fun withdraw(sum: Int): Boolean {
    return if (isBlocked) false else if (sum < 0 || sum > bound) false else if (balance <= maxCredit + sum) false else {
        balance -= sum
        true
    }
}
```
#### New:
```kotlin
fun withdraw(sum: Int): Boolean {
    if (abs(balance + sum) > bound) {
        return false
    }
    return if (isBlocked) false else if (sum < 0 || sum > bound) false else if (balance <= maxCredit + sum) false else {
        balance -= sum
        true
    }
}
```

#### Created and powered by Matvey Popov