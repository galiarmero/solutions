---
categories: array
---

# [Plus One](https://leetcode.com/problems/plus-one/)

Given an array of digits representing a non-negative integer, we have to add `1` to the integer and return the sum's array of digits.

We can solve this in a similar way to how we add multi-digit numbers on paper as we learned in grade school. We need to start adding `1` at the ones place, in this case that's the last element of the array. If the sum is `>= 10`, we put the last digit and carry over `1` to the next higher place value. Actually, in this problem, since we are only adding one, the maximum result of digits is always `10`.

For example, if the number is `459`, since `9` + `1` is `10`, we store `0` in the ones place and carry over `1` to the next place. For the tens place, we add `5` and the carried over `1`. That's `6` which is not `>= 10`. So we don't need to carry over at this point. We can simply copy the remaining digits

|       |   |   |   |   |
|-------|---|---|---|---|
| Carry |   |   | 1 |   |
|       |   | 4 | 5 | 9 |
| +     |   |   |   | 1 |
| Sum   |   | 4 | 6 | 0 |

In fact, we can update the array of digits in place.

```java
for (int i = digits.length - 1; i >= 0; i--) {
    int sum = (digits[i] + 1);
    digits[i] = sum % 10;

    if (sum < 10) return digits;
}
```

Now, what if we have to carry over `1` until there are no more digits? That will only happen if the numbers are composed of all `9`s. Like so:

|       |   |   |   |   |
|-------|---|---|---|---|
| Carry | 1 | 1 | 1 |   |
|       |   | 9 | 9 | 9 |
| +     |   |   |   | 1 |
| Sum   | 1 | 0 | 0 | 0 |

If the loop ends and the sum for the highest place value is still `10`, we simply create a new array that is 1 element longer, then initialize the first element to `1`. The rest will of the elements will automatically be zeroes.

```java
int[] carryOne = new int[digits.length + 1];
carryOne[0] = 1;
return carryOne;
```
