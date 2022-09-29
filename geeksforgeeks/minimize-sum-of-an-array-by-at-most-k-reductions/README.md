---
categories: priority queue,heap
---

# [Minimize Sum of an Array by at Most K Reductions](https://www.geeksforgeeks.org/minimize-sum-of-an-array-by-at-most-k-reductions/)

Here's the specification in [GeeksforGeeks](https://www.geeksforgeeks.org/minimize-sum-of-an-array-by-at-most-k-reductions/):

> Given an array of integers `arr[]` consisting of `N` integers, the task is to minimize the sum of the given array by performing at most `K` operations, where each operation involves reducing an array element `arr[i]` to `floor(arr[i]/2)`.

The key here is at every operation, we should pick and reduce the largest element in `arr[]`. This gives us the most optimal way to yield the most minimized sum.

The next question is how to get the max element at each operation. If check each element in `arr` one-by-one to end up with the max, the solution will run in _O(K N)_.

Using a [max heap](https://www.geeksforgeeks.org/max-heap-in-java/) is a quicker way as it takes _O(log N)_ to retrieve the max value. This would make the solution run in _O(K log N)_. In Java, we can use the `PriorityQueue` implementation and set a comparator that imposes a descending order.

So, to get the minimized sum:

1. Push all the elements of `arr` into the `maxHeap`
2. For `K` times, do these steps:
   1. Poll the max value from the `maxHeap`
   2. Divide the value by `2`
   3. Add it back to the `maxHeap`
3. Poll the elements of the `maxHeap` one by one and get their sum until it's empty
