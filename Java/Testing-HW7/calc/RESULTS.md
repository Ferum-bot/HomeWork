# Fuzzer results
## Total suites `#7954740`. Founded bugs:
## № 1
```java
== Java Exception: java.lang.NullPointerException
        at calc.Calc.opn(Calc.java:34)
        at calc.Calc.opnAndCalculate(Calc.java:22)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
DEDUP_TOKEN: 5f5ccc47e01f2864
== libFuzzer crashing input ==
MS: 0 ; base unit: 0000000000000000000000000000000000000000

```
### Fixed by:
```java
 public static String opn(String sIn) throws CalcException{
        if(sIn == null) {
            throw new CalcException("Input can't be null");
        }
        StringBuilder sbStack=new StringBuilder(),sbOut=new StringBuilder();
        char cIn,cTmp;
        ...
    }
```

## № 2
```java
== Java Exception: java.util.NoSuchElementException
        at java.base/java.util.ArrayDeque.removeFirst(ArrayDeque.java:363)
        at java.base/java.util.ArrayDeque.pop(ArrayDeque.java:594)
        at calc.Calc.calculate(Calc.java:171)
        at calc.Calc.opnAndCalculate(Calc.java:22)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
DEDUP_TOKEN: 4a3d1d8ebd6ee815
== libFuzzer crashing input ==
MS: 0 ; base unit: 0000000000000000000000000000000000000000
```

### Fixed by:
```java
        if (stack.size() != 1) {
            throw new CalcException("Количество операторов не соответствует количеству операндов");
        }
```

## № 3
```java
== Java Exception: java.lang.NumberFormatException: For input string: "͍"
        at java.base/jdk.internal.math.FloatingDecimal.readJavaFormatString(FloatingDecimal.java:2054)
        at java.base/jdk.internal.math.FloatingDecimal.parseDouble(FloatingDecimal.java:110)
        at java.base/java.lang.Double.parseDouble(Double.java:543)
        at calc.Calc.calculate(Calc.java:159)
        at calc.Calc.opnAndCalculate(Calc.java:22)
        at jdk.internal.reflect.GeneratedMethodAccessor24.invoke(Unknown Source)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
DEDUP_TOKEN: 7f8b52f54fec6c67
== libFuzzer crashing input ==
MS: 4 ChangeByte-InsertByte-ShuffleBytes-InsertByte-; base unit: adc83b19e793491b1c6ea0fd8b46cd9f32e592fc
```

### Fixed by:
```java
} catch (CalcException e) {
    throw new CalcException("Недопустимый символ в выражении");
} catch (NumberFormatException e) {
    throw new CalcException("Invalid input format");
}
```

## № 4
```java
== Java Exception: java.lang.StringIndexOutOfBoundsException: start -1, end 0, length 0
        at java.base/java.lang.AbstractStringBuilder.checkRangeSIOOBE(AbstractStringBuilder.java:1724)
        at java.base/java.lang.AbstractStringBuilder.substring(AbstractStringBuilder.java:1017)
        at java.base/java.lang.StringBuilder.substring(StringBuilder.java:90)
        at java.base/java.lang.AbstractStringBuilder.substring(AbstractStringBuilder.java:968)
        at java.base/java.lang.StringBuilder.substring(StringBuilder.java:90)
        at calc.Calc.opn(Calc.java:55)
        at calc.Calc.opnAndCalculate(Calc.java:22)
        at jdk.internal.reflect.GeneratedMethodAccessor30.invoke(Unknown Source)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
        DEDUP_TOKEN: 7d0de83074e7f08b
        == libFuzzer crashing input ==
        MS: 1 CMP- DE: ")\000"-; base unit: 44fb13e713e02e3a262f07681181f0e3beabbbe7
```

### Fixed by:
```java
} else if (')' == cIn && sbStack.length() != 0) {
```

## № 5
```java
== Java Exception: java.lang.StringIndexOutOfBoundsException: start -1, end 0, length 0
        at java.base/java.lang.AbstractStringBuilder.checkRangeSIOOBE(AbstractStringBuilder.java:1724)
        at java.base/java.lang.AbstractStringBuilder.substring(AbstractStringBuilder.java:1017)
        at java.base/java.lang.StringBuilder.substring(StringBuilder.java:90)
        at java.base/java.lang.AbstractStringBuilder.substring(AbstractStringBuilder.java:968)
        at java.base/java.lang.StringBuilder.substring(StringBuilder.java:90)
        at calc.Calc.opn(Calc.java:62)
        at calc.Calc.opnAndCalculate(Calc.java:22)
        at jdk.internal.reflect.GeneratedMethodAccessor29.invoke(Unknown Source)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
DEDUP_TOKEN: 1716a352c0a6d606
== libFuzzer crashing input ==
MS: 1 ChangeBit-; base unit: 83dcac5ca7d4dbdea55b6faef8215ae66a1487a7
```

### Fixed by:
```java
     while ('(' != cTmp) {
        if (sbStack.length() <= 1) {
            throw new CalcException("Ошибка разбора скобок. Проверьте правильность выражения.");
        }
```

## № 6
```java
== Java Exception: java.lang.StringIndexOutOfBoundsException: String index out of range: 1
        at java.base/java.lang.StringLatin1.charAt(StringLatin1.java:47)
        at java.base/java.lang.String.charAt(String.java:693)
        at calc.Calc.calculate(Calc.java:133)
        at calc.Calc.opnAndCalculate(Calc.java:22)
        at jdk.internal.reflect.GeneratedMethodAccessor30.invoke(Unknown Source)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
DEDUP_TOKEN: 24c2ec2159b6c9ee
== libFuzzer crashing input ==
MS: 5 ChangeByte-ShuffleBytes-ChangeByte-ChangeByte-EraseBytes-; base unit: 09f1978db275596d8939cfbb3b063da8a67e2769
```

### Fixed by:
```java
    } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
        throw new CalcException("Invalid input format");
    }
```

#### Created and powered by Matvey Popov