---
categories: binary search,math
---

# [Sqrt(x)](https://leetcode.com/problems/sqrtx/)

The linear approach would be to iterate over each integer `i` from `0` up to `x` (`[0, 1, 2, ..., n]`), checking if we have found the square root each time with these specific cases:

-  If `i * i == x`, `i` is our square root. Return `i`. Applicable for cases where `x` is a perfect square (e.g. `sqrt(9)`, `sqrt(36)`)
-  If `i * i > x`, the previous int `i - 1` is the integer part of the truncated square root. Return `i - 1`.

The fact that the sequence we are iterating is sorted is an indicator that we can potentially use binary search to make the solution quicker. In the solution for [First Bad Version](../278-first-bad-version), I mentioned a great [Topcoder article](https://www.topcoder.com/thrive/articles/Binary%20Search) that explains how we can use binary search in such scenarios.

I learned there that we can use binary search on a sequence if you could ask a yes-or-no question (called predicate) and that would yield you a series of _no's_ followed by a series of _yeses_. Binary search would be great in determining the items having the last `no` and the first `yes` in that sequence. Let me illustrate with this problem.

Given an `x = 8`, we know the answer is `2`.

We need to see if we can come up with a yes-or-no question that when asked for each `i` would yield us a sequence where `2` has the last `no`, followed by a series of `yes` answers:

| 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
| - | - | - | - | - | - | - | - | - |
| no | no | **no** | yes | yes | yes | yes | yes | yes |


...or `2` has the first `yes` in a series of `yes` answers:

| 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
| - | - | - | - | - | - | - | - | - |
| no | no | **yes** | yes | yes | yes | yes | yes | yes |

These two outcomes would make it easy to do binary search on the sequence.

For the first outcome, I can think of a yes-or-no question that would work: **Is the product of this number multiplied by itself greater than `x`?**. `2 * 2 = 4` which is less than `8` so that's a _no_. But the next number, `3 * 3 = 9` is greater than 8 so that's a yes. That looks like a fitting predicate to use.

For the second outcome, it's quite difficult to find a question that would yield a `yes` on `2` (without using sqrt or pow functions of course). The best I have is: **Is the product of the _next_ number multiplied by itself greater than `x`?** but that's basically similar to the first question, innit?

Let's just use the first question and plug that in to the pseudocode provided in that Topcoder article.

```python
binary_search(n):
    left = 0, right = n
    while left < right:
        mid = left + (right - left) / 2
        if mid * mid > x:
            right = mid
        else:
            left = mid + 1

    return left - 1
```

Notice in  the last line we're returning `left - 1`. `left` is the first item with a `yes`. In the specific question we're asking, we know the item with the last `no` is the answer.

## Edge cases

- If `x` is `0` or `1`, the pseudocode above won't work. We need to add a special handling at the start.
- In Java, if `mid` is an `int`, `mid * mid` might cause an overflow. Use `long` instead.
