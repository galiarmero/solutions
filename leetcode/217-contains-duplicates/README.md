---
topics: array,set
---

# [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/)

There are two potentially ideal approaches:

**A.** Sort the `nums` first. Duplicate elements will then be beside each other. Then, check in a single pass if the current `num` is identical to the adjacent element. This solution will be as fast as the sorting algorithm being used -- in Java, Collections.sort is O(n log(n)) -- but the space complexity is constant.

**B.** Use a hash table to check if an element has been encountered previously within the array. `Set` would be more optimal over `Map` since we only need to keep track of a single dimension, the number itself. So, no need for a key-value entry. The time complexity is linear but the with the need to create a hash table, space complexity is linear as well.

We realize here the time-space tradeoff. If you value space usage more, **A** would be the best approach. If we value faster runtime more, **B** is ideal. Take a pick.
