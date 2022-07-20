---
topics: string
---

# [Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)

To better visualize how we will solve it, let's look at how we can validate a plain old palindrome first. The efficient approach is to check each character from the front to the middle of the string if it's the same with the opposite character on the other half of the string.

Say for example we have `"adzda"`.


| 0 | 1 | 2 | 3 | 4 |
|---|---|---|---|---|
| a | d | z | d | a |

We can have `front` and `back` indices to track what we are comparing. Initially we have `front` = **`0`** and `back` = **`4`** to compare if `'a'` and `'a'` are the same.

| <ins>0</ins> | 1 | 2 | 3 | <ins>4</ins> |
|---|---|---|---|---|
| <ins>a</ins> | d | z | d | <ins>a</ins> |

Since they are still equal, we proceed to the next iteration, moving the indexes one position closer to the middle by increasing `front` and decreasing `back` by one. `front` = **`1`** and `back` = **`3`**. The characters are the same.

| 0 | <ins>1</ins> | 2 | <ins>3</ins> | 4 |
|---|---|---|---|---|
| a | <ins>d</ins> | z | <ins>d</ins> | a |

We can skip the next iteration since `front` and `back` would both equal to **`2`**, pointing to the same character. Since all characters compared were equal, we can conclude that the string is a palindrome.

---

Now, we take this `front`-and-`back` approach to solve this problem, but with some tweaks:

1. We only compare if both the `front` and `back` characters are alphanumeric.
2. If the character on the `front` is _not_ alphanumeric, skip it by increasing `front` by one. Similarly if the character on the `back` is _not_ alphanumeric, skip it by decreasing `back` by one.


Repeat the checks until _just before_ `front` is equal to `back`.





## Testcases

```java
"A man, a plan, a canal: Panama"
"race a car"
" "
"aB2bA"
```