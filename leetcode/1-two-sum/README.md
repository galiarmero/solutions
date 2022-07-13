---
topics: array,map
---

# [Two Sum](https://leetcode.com/problems/two-sum/)

A slower, brute-force solution is to check every unnique pair of integers in `nums` if their sum is the `target` integer. This can be done using a nested for-loop.

But it's possible to arrive at the same result in a single pass. At each iteration, a lookup will be used to check in constant time if we already encountered in previous iterations an integer that, when added to the current integer (`nums[i]`), would yield the `target`.

```md
for each `index`, `num` in `nums`:
    if (`target` - `num`) is in `lookup`:
        return [ `lookup[target - num]`, `index` ]
    otherwise, store the `index` in `lookup[num]`
```
