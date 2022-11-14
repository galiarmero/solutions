---
categories: string,array
---

# [Convert a given number to words](https://www.geeksforgeeks.org/convert-number-to-words/)

The key is to process a number's digits from left to right. Each digit is expressed in the format `{digit-word} {place}` (e.g. `two thousand`, `three hundred`). The combination of all the words for each digit is the result.

So, to process each digit and get its place, say starting with the thousands:

```py
place = 1000
while number > 0:
    digit = number / place;
    
    number %= place # First digit done. Cut it from the number
    place /= 10 # The next place to process
```

But it gets tricky when the remaining number is less than 100. English is quirky when expressing these numbers.

When `20 <= number < 100`, instead of a suffix we have some special prefixes to use: `twenty`, `thirty`, etc. But the same rule flow applies, we express the first digit in "tens" words, then append the conversion of the last digit.

So `99` becomes `ninety-nine`, `68` becomes `sixty-eight`, you get the drill.

When `0 <= number < 20`, we throw the digit-by-digit processing and use the single-word terms for these numbers: `one`, `two` ... `eleven`, `twelve`, and so on.


In summary, one way to approach this is to process the more straightforward digits first when the `number >= 100` in one loop. Then whatever is left, process it after with the special rules.

```py
place = 1000
while number >= 100:
    digit = number / place

    result += # the digit + suffix 

    number %= place # First digit done. Cut it from the number
    place /= 10 # The next place to process

while number > 0:
    if number >= 20:
        result += tens[number / 10]
        number %= 10
        # add separator '-' or ' ' to result if there's still left
    else:
        result += twenty_below[number]
        number = 0
```
