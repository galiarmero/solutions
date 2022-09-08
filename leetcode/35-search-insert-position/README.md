---
categories: binary search,array
---

# [Search Insert Position](https://leetcode.com/problems/search-insert-position/)

I discussed in my solutions to [First Bad Version](../278-first-bad-version/) and [Sqrt(x)](../69-sqrtx/) how we can identify if a problem is solvable using binary search. If you are currently in the business of solving binary search problems, I suggest reading the [Topcoder article](https://www.topcoder.com/thrive/articles/Binary%20Search) I mentioned in those solutions. In this article, you'll learn that:

1. Binary search is quick at finding values in a sequence that follows some order.
2. It would likely work in a sequence `S` if you can apply a predicate (ask a yes-or-no question) about each element `x` and the resulting sequence of answers is a series of _no's_ followed by a series of _yeses_. It's good at finding the **first yes** (and also the **last no**) in that sequence.

This problem checks the first item. `nums` is sorted.

The challenge, then, is to check if we can ask the _right question_. What would be a good yes-or-no question that will help us solve this? I found in these sort of problems that reverse engineering is a useful exercise.

If the `target` is `5` in a sequence of `[1, 3, 5, 6]`, what would be a good question to ask such that `5` starts sequence of _yeses_?

| 1 | 3 | 5 | 6 |
| - | - | - | - |
| no | no | **yes** | yes |

That's easy: **Is x >= target?**

It would also be easy to solve if `5` is having the last `no`. Let's see if we can find out a fitting question to generate such sequence.

| 1 | 3 | 5 | 6 |
| - | - | - | - |
| no | no | **no** | yes |

In this case, a good question would be: **Is x > target?**

We've now collected two questions so far:
- **Is x >= target?**
- **Is x > target?**

Let's test them out in a different scenario. What if `target` `5` is not in the sequence, say `[1, 3, 4, 6, 7]`? We want index `3`, the position occupied by `6` as the result.

| 1 | 3 | 4 | 6 | 7 |
| - | - | - | - | - |
| no | no | no | **yes** | yes |

Our two questions are good candidates still. `6 >= 5` and `6 > 5`.

Let's also see if we can ask a question to make `6` have the last `no`.

| 1 | 3 | 4 | 6 | 7 |
| - | - | - | - | - |
| no | no | no | **no** | yes |

In relation to `5`, it's difficult to find a good question that would give a `no` for `6` but `yes` for `7` (without referencing the previous element). So let's settle with the question where, whether the `target` is in the sequence or not, the insert position has the _first yes_: **Is x >= target?**

From here, it's all about plugging the yes-or-no question we generated into the snippet from the Topcoder article:

```python
binary_search(nums, target):
    left = 0, right = len(nums)
    while left < right:
        mid = left + (right - left) / 2
        if nums[mid] >= target:
            right = mid
        else:
            left = mid + 1

    return left
```

One thing to note: `right` is assigned with the `len(nums)` which is the vacant slot after the last element. That means that slot will be considered as a valid position in case the `target` is greater than all the elements in the sequence. You'll notice that last slot will be the last `left` in such case. But it will then exit the loop since `left` is already equal to `right`.
