---
categories: array,heap,sort,priority queue
---

# [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)

We can divide this problem into two smaller, more manageable subproblems:

1. Count the frequency of each `num`
2. Determine the top `k` nums with the highest frequencies


## Counting frequencies

We can keep track of the frequencies by using a hash table `frequency`. For each `num` in `nums`, increment the count of `frequency[num]` by 1.

```python
frequency = map()
for num in nums:
    if num in frequency:
        frequency[num] = 1
    else:
        frequency[num] = frequency[num] + 1
```

## Determine top `k` frequent nums

Now that we have calculated the frequency of each `num`, we now have data to identify the top `k` most frequent numbers.

There can be two approaches here:

1. Use an array of unique keys in `frequency` (i.e. unique integers from `nums`), sort the array in descending order, then get the subset of the array from index `[0...k-1]`.
2. Create a max heap (priority queue) with the priority being the `frequency` of each element, push the unique keys in `frequency` into the max heap, and poll the first `k` elements.
