---
categories: tree,binary tree,binary search tree
---

# [Convert Sorted Array to Binary Search Tree](https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/)

Quick refresher on the properties of a binary search tree:

- The values on the left sub-tree is less than the value of the node itself
- The values on the right sub-tree is greater than the value of the node itself
- The left sub-tree and right sub-tree are also both binary search trees

With this definition in mind, if we are given a sorted array, the sensible approach is to make the element in the middle position the root. Then, we construct the left sub-tree using the left slice from the middle and construct the right sub-tree using the right half of the middle. Since both sub-trees are also binary search tree, we can use recursion and call the same method to build them.

Here's a pseudocode of the recursive method:

```python
sortedArrayToBST(nums[]):
    if `nums` is null or empty:
        return null
    int midpoint = nums.length / 2
    return TreeNode(nums[midpoint],
        sortedArrayToBST(nums[0...midpoint]), # left half as the left sub-tree
        sortedArrayToBST(nums[midpoint + 1, nums.length]) # right half as the right sub-tree
    )
```
