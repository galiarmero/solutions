---
categories: binary search,array
---

# [Capacity To Ship Packages Within D Days](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/)

Given a list of `weights` of packages, we must be able to ship them _in order_ in `D` days. The task is to calculate the least weight capacity of the ship that will allow us to do this.

We can divide this into two smaller, more manageable problems:

1. Have the ability to determine if a given capacity `m` is able to ship the packages in `D` days.
2. Get the mininmum capacity that can ship the packages in `D` days from a range of candidate capacities

Let's tackle each of these one-by-one.

## 1. Is a ship's `capacity` able to transport `weights` in `D` days?

We can do this ad-hoc. We will basically go over each `w` in `weights`, batching them together until their `total` weight exceeds `capacity`, in which case we have to ship `w` the next day. So we maintain a running count of `days` we need so far. If this `days` exceeds the demanded `D`, it's not possible to ship the packages with this `capacity`.

Let's call the function `isPossibleWithCapacity`.

```python
isPossibleWithCapacity(capacity, weights, D)
    days = 1, total = 0

    for w in weights:
        total += weight
        if total > capacity: # Exceeds capacity, ship it the next day
            total = weight
            days++

            if (days > D): return false

    return true
```

## 2. Calculate the minimum ship `capacity` able to transport `weights` in `D` days

We now have `isPossibleWithCapacity` which can help determine if a `capacity` can fulfill the requirements. The next problem is what is the range of capacities do we check?

The slowest possible way is to ship one package every day. Maybe the `D` specified allows us to do that. If we're going to do that, we need a `capacity` of at least `max(weights)`. If it's less than that, we can't ship the heaviest package. Let's use that as the lower bound.

The quickest possible way is to ship all the packages at once. Perhaps, `D` is `1` so we're in a rush. To make that possible, the ship's `capacity` has to be `sum(weights)`. That way, we can accommodate all the `weights`. That would be our lower bound.

So now the range is clear: `[min(weights), ..., sum(weights)]`. We'll probe each element in that sequence against `isPossibleWithCapacity` to determine the minimum capacity required.

### Linear search

For each `c` in `[min(weights), ..., sum(weights)]`, if `isPossibleWithCapacity(c, weights, D)`, then `c` is the minimum capacity needed. Return `c` immediately.

However, this is painfully slow. What if `D` is `1`? Then we have to go all the way to the last element in the sequence.

### Binary search

If you've read my solution to [First Bad Version](../278-first-bad-version/), [Sqrt(x)](../69-sqrtx/), or [Search Insert Position](../35-search-insert-position/), you might remember this [Topcoder article](https://www.topcoder.com/thrive/articles/Binary%20Search) tackling binary search and when we can use them. If not, I suggest looking at those problems first.

This problem fits the criteria. `[min(weights), ..., sum(weights)]` is sorted and we can generate a sequence of booleans by applying `isPossibleWithCapacity` on each element, with the resulting sequence having a series of _no's_ followed by a series of _yeses_.

In this pseudocode, we can quickly find the minimum capacity that gets the job done. With binary search, we are able to halve the search space each iteration, using `isPossibleWithCapacity` to decide whether we search the left side or right side of the midpoint next.

```python
binary_search(weights, D):
    left = min(weights), right = sum(weights)
    while left < right:
        mid = left + (right - left) / 2
        if isPossibleWithCapacity(mid, weights, D):
            right = mid
        else:
            left = mid + 1

    return left
```

### Java tip

To get the min or sum of the `weights`, we can use utilities provided by the `IntStream` or `Collections` class. But I was able to save a few milliseconds of runtime by implementing them using a simple loop.
