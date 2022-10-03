---
categories: tree,depth-first search
---

# [Same Tree](https://leetcode.com/problems/same-tree/)

We have to check if two binary trees are the same or not. The problem specifies how two trees are considered the same:

> Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

Another way to look at it is given the root of both trees `p` and `q`, they are _the same_ if all these criteria are satisfied:

1. `p.val` should be equal to `q.val`
2. **and** `p.left` should be _the same_ as `q.left`
3. **and** `p.right` should be _the same_ as `q.right`

**#1** is trivial. But how do we determine **#2** and **#3**? Well, the left and right sub-trees of a binary tree are also binary trees! That means we can re-use the same method we'll create to recursively check if the left and right sub-trees of the two root nodes are the same. A subsequent call to check if `p.left` and `q.left` are the same will also check their own left and right sub-trees, and so on until the deepest nodes are reached.

Here is a pseudocode that implements the checks using depth-first search (DFS):

```python
isSameTree(p, q):
    if p == null or q == null
        return p == q
    if p.val == q.val
        # The nodes have the same value
        # Now, time to check if their left and right sub-trees are the same
        return isSameTree(p.left, q.left) and isSameTree(p.right, q.right)

    ## The nodes' values are not equal. That means the two trees are not equal.
    return false
```
