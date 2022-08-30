---
categories: map,string,array
---

# [Roman to Integer](https://leetcode.com/problems/roman-to-integer/)

Using a map to look up the integer equivalent of a Roman numeral character will make `O(n)` possible.

```java
Map<Character, Integer> intValues = Map.of(
    'I', 1,
    'V', 5,
    'X', 10,
    'L', 50,
    'C', 100,
    'D', 500,
    'M', 1000
);
```

For `i` starting from `0` to `s.length() - 1`, the character `c` being `s[i]`:

1. If `c` is an `'I'`, `'X'`, or `'C'`, check if this is one of the special "subtractive notation" cases
    - If it's not yet the last `c` in the string and `intValues[c] < intValues[d]` where `d` is the character after `c` (`s[i + 1]`), then apply the subtractive approach:
        - That means adding `intValues[d] - intValues[c]` to the `result`
        - Then bump `i` by `2` steps
    - Otherwise, the value is not subtractive so proceed to step **2**.
2. Otherwise, just add `intValues[c]` to the `result` and increment `i` by 1


## Shaving a few off the runtime

Since the set of characters for a Roman numeral is known and fixed, we can use an integer array for the lookup instead of a map. The overhead for hashing character keys in maps is avoided, reducing the runtime when accessing the integer equivalent.

We will use the ASCII offset from character `'C'` as the index. Thus, the value for `'C'` will be in index `0` while `X` will be in index `21`. We need at least 22 elements for this, `X` being the last character we need.

```java
int[] intValues = new int[22];
intValues['I' - 'A'] = 1;
intValues['V' - 'A'] = 5;
intValues['X' - 'A'] = 10;
intValues['L' - 'A'] = 50;
intValues['C' - 'A'] = 100;
intValues['D' - 'A'] = 500;
intValues['M' - 'A'] = 1000;
```

In exchange for some performance gains, we're trading off:

- A few more blocks of memory by requiring _22_ slots when there are only _7_ roman characters. But this is negligible since hash table implementations will also add some slots as buffer (to avoid frequent collisions).
- Readability. `integerValues.get(c)` is much more readable than `integerValues[c - 'C']`.

The choice is yours. 😉
