---
categories: binary search,array
---

# [Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/)

Given `piles` of bananas and an `h`-hour limit, the task is to determine the minimum number of bananas-per-hour that Koko can consume (we'll call this `bananasPerHour`) so that she can finish all bananas within `h` hours.

To make it easier to wrap our heads around this problem, let's identify the two smaller, more manageable problems we need to solve:

1. Determine if a given integer `bananasPerHour` can allow Koko to finish all the `piles` of bananas in `h` hours or less
2. Given a range of feasible `bananasPerHour`s, identify the minimum `bananasPerHour` that can allow Koko to finish all the `piles` of bananas in `h` hours or less

Now we just need to tackle each of these subproblems one-by-one.

## 1. Can a given `bananasPerHour` allow Koko to finish all `piles` within `h` hours?

We'll delegate this problem to a function `boolean canFinish(bananasPerHour)`

For each `pile`, we need to compute the hours Koko will spend to finish it.

- If `pile` = `2`, `bananasPerHour` = `3`: Koko will spend `1` hour to finish the pile
- If `pile` = `6`, `bananasPerHour` = `3`: Koko will spend `2` hours to finish the pile
- If `pile` = `8`, `bananasPerHour` = `3`: Koko will spend `3` hours to finish the pile

Notice that even if the bananas left in the `pile` is less than the `bananasPerHour`

The hours spent to finish a `pile` can be calculated by dividing `pile` with `bananasPerHour` and then adding an extra _1_ to the quotient if there's a remained. This is essentially getting the ceiling of the quotient. In Java, there's [Math.ceil](https://docs.oracle.com/javase/7/docs/api/java/lang/Math.html#ceil(double))

```java
(int) Math.ceil((double) pile / bananasPerHour)
```

But I found `Math.ceil` to be waaay slower. This expression, although less intuitive, provides the same result faster (30ms savings in runtime just by switching to this):

```java
(pile + bananasPerHour - 1) / bananasPerHour
```

We'll add this result to `hoursSpent`, the total hours it will take to finish all the `piles`.

```java
hoursSpent += (pile + bananasPerHour - 1) / bananasPerHour
```

After calculating for all the `piles`, if `hoursSpent <= h`, return `true` because Koko can finish them within `h`! Otherwise, return `false`.

Onto the next subproblem!

## 2. Given a range of feasible `bananasPerHour`s, what's the minimum that allows Koko to finish all of the `piles` in `h` hours?

Obviously, the slowest Koko can go is eating **1** `bananasPerHour`. The fastest? Well, she can eat a whole pile every hour. She can do that if `bananasPerHour` is at least equal to the biggest pile.

There's the range we are looking for.

```py
candidates = [1, 2, ..., max(piles)]
```

The _O(n)_ way to get the minimum `k` in `candidates` where `canFinish(k)` is `true` is to simply loop over the entire range. The first `k` that returns `true` on `canFinish(k)` is the answer.

But notice that this range is sorted, such that if `canFinish(k)` is `true`, then `canFinish(k + 1)` will surely also be `true`. That's a hint that we can employ binary search to find the result in _O(log n)_.

We can initially start at the midpoint of the entire range. If `canFinish(midpoint)` is `true`, use the left side of the midpoint as the next search space and disregard the right side because surely, elements there will also result to `true`. Otherwise, use the right side as the next search space, since the answer will surely be on that side. Here's a snippet of these simple steps:

```java
public int minEatingSpeed(int[] piles, int h) {
    int lo = 1;
    int hi = max(piles);
    int midpoint;

    while (lo < hi) {
        midpoint =  (hi - lo) / 2 + lo;

        if (canFinish(piles, h, midpoint)) {
            hi = midpoint;
        } else {
            lo = midpoint + 1;
        }
    }

    return lo;
}
```

See [Solution.java](./Solution.java) for the the entire solution with test cases.