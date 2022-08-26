---
categories: map,string
---

# [Integer to Roman](https://leetcode.com/problems/integer-to-roman/)


I tried two approaches. The first (personally) more intuitive approach turned out to be not fast and memory-efficient enough. I used a map here. The second turned out to be faster and used a little less space.

## By-place-values approach

This is how I would probably solve it on a paper. For a given `num`, extract each place value, convert a place value to roman numeral and append it to the result.

For example, if `num` is `1993`, the place values would be `1000, 900, 90, 3`. Converting each of these into roman numeral will give us `'M', 'CM', 'XC', 'III'`. The result is the concatenation of all these symbols: `MCMXCIII`.

Seems pretty straightforward but the devil (that makes it slower programmatically) is in the details.

Firstly, this would need a map of roman symbols to make lookup faster. This would consume a little more space and time than an array.

Second, to extract each place value on each iteration, you need to carry out a series of arithmetic operations and maintain some variables to iterate:

```java
int place = 1000;
while (num > 0) {
    int digit = num / place;

    // Get the roman equivalent of `digit * place`

    num -= (digit * place);
    place /= 10;
}
```

Lastly, getting the roman equivalent of the place value will also require handling different cases:

- `4`s and `9`s
- value is `< 4`
- value is `> 5`

This will also entail some repetitive concatenations. See `intToRomanByPlaceValue` in [Solution.java](./Solution.java) for the sample code for this approach.

This approach looks easier on paper but a little less readable when translated in code.

## Subtract-convert-repeat approach

For a given `num`, we can:

1. Try to subtract from `num` the biggest possible roman numeral denomination we can get out of it
2. Append the corresponding symbol of the denomination in the `result` string
3. Repeat until `num` is now zero

For example, we have these denominations and their corresponding symbols in separate arrays:

```java
values = [1000, 900, 500, 400, 100, ...];
symbols = ["M", "CM", "D", "CD", "C", ...];
```

Given a `num` of `1993` and `result` starting empty:
```java
result = ""
num = 1993
```

The biggest denomination we can subtract is `1000`. `1993` is made up of **1** `1000` so we append an `"M"` in the result.
```java
result = "M"
num = 993
```

Next, we are left with `993` after subtracting `1000`. The biggest value we can subtract now is `900`. So we append `"CM"`.

```java
result = "MCM"
num = 93
```

Now, the biggest we can subtract is `90`. That's `"XC"`, so we append that. Which leaves us with a `num` of `3`.

```java
result = "MCMXC"
num = 3
```

We can't subtract `5` or `4`. We can only subtract `1`. So we do so repeatedly. Until we're left with `0`. At last, we can return the `result`.

```java
result = "MCMXCI"
num = 2


result = "MCMXCII"
num = 1

result = "MCMXCIII"
num = 0
```

## Java caveats

It turns out using `String`'s `+` operator is way more expensive than using `StringBuilder.append()`. It gets more noticeable as more appends are done.

Since `String` is immutable, each `s1 += s2` will create a new string instance (eats more memory), initialize it with `s1` plus `s2`, then assign that instance back to `s1` (takes more time).
