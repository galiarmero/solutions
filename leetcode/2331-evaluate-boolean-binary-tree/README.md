---
categories: tree,depth-first search
---

# [Evaluate Boolean Binary Tree](https://leetcode.com/problems/evaluate-boolean-binary-tree/)

We are tasked to evaluate a binary tree where each node has the following values and implications:

- `0` => the `node` evaluates to `false`
- `1` => the `node` evaluates to `true`
- `2` => the `node` evaluates to `node.left OR node.right`
- `3` => the `node` evaluates to `node.left AND node.right`

We cannot evaluate the entire tree without evaluating the deepest sub-trees first. Thus, we have to do depth-first search (DFS). The evaluation rules will apply to all sub-trees so we can just re-use the same function to recursively evaluate each sub-tree.

Here's a pseudocode that simply applies the rules:

```python
evaluateTree(tree):
    if tree.val == 0:
        return False
    if tree.val == 1:
        return True
    if tree.val == 2:
        return evaluateTree(tree.left) OR evaluateTree(tree.right)

    # tree.val == 3
    return evaluateTree(tree.left) AND evaluateTree(tree.right)
```

To avoid unnecessary processing, ensure the use of short-circuited evaluation (e.g. `||` and `&&` in Java) as it would skip evaluating the right sub-tree if the end result is already certain by evaluating just the left sub-tree. This happens when:

- `evaluateTree(tree.left)` is `true` and the operator is an OR: will always be `true`
- `evaluateTree(tree.left)` is `false` and the operator is an AND: will always be `false`
