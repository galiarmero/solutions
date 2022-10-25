---
categories: array,heap,sort
---

# [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/)

There are several ways to approach this.

One is to use a max heap (using the `PriorityQueue`) implementation in Java. Add each element `num` from `nums` into the max heap. Then, `poll` the max heap `k` times. The `k`th element polled is the answer.

But the simplest, and even faster solution than the previous one is to simply sort the array. The time complexity for the said operation is _O(n log n)_. Once sorted, return the kth largest element. If ascending, that's index `nums.length - k`. If descending, that's index `k - 1`.
