---
categories: array,dynamic programming
---

# [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/)

Given `nums`, the task is to generate a `products` such that `products[i]` is the product of all `nums` _except_ `nums[i]`. The challenge is with the restrictions: the solution should be `O(n)` and we can't use any division.

## The subproblems 

We can make this possible by dividing it into smaller subproblems. 

1. For each num (`nums[i]`), get the product of all `nums` on its _left_, store it in `left[i]`
2. For each num (`nums[i]`), get the product of all `nums` on its _right_, store it in `right[i]`
3. For each num (`nums[i]`), get the product of `left[i]` and `right[i]` and put it on `results[i]`

## Example

Say we have these `nums`, top row being the indices (`i`):

`nums`:
| 0 | 1 | 2 | 3 |
| - | - | - | - |
| 1 | 2 | 3 | 4 |

If we want to get the `results[2]` which is product of array except `3`, then **1**, we have to get the products of nums on its left (see underlined):

`nums`:
| 0 | 1 | 2 | 3 |
| - | - | - | - |
| <ins>1</ins> | <ins>2</ins> | **3** | 4 |

`left[2]` would then be `3` (1 * 2). Next, we get the product of nums on its right. There's only one entry on its right.

`nums`:
| 0 | 1 | 2 | 3 |
| - | - | - | - |
| 1 | 2 | **3** | <ins>4</ins> |


`right[2]` would be `4`.
```
result[2] = left[2] * right[2]
result[2] = 3 * 4
result[2] = 12
```

## Tackling the subproblems

To solve subproblem **1** and **2**, we can utilize dynamic programming to keep it at `O(n)` since the result of an iteration has an overlap with the previous iteration's.

That means to get `left[i]`, we can simply use `left[i - 1]` and multiply it with `nums[i - 1]`. This is much quicker than iterating from `nums[0]...nums[n]`.

To generate the left products, we set `left[0]` to `1` since there's nothing on its left. Then proceed with the succeeding calculations.
```python
left[0] = 1
for i in 1...n - 1
    left[i] = left[i - 1] * nums[i - 1]
```

For the right, it's basically the same but we start from the back, setting `right[n - 1]` to 1 since it has nothing on its right.
```python
right[n - 1] = 1
for i in n - 2...0
    right[i] = right[i + 1] * nums[i + 1]
```

Solving subproblem **3** is as easy as iterating from 0...n to populate `results`.

## Further optimizations

We can actually combine subproblem **2** and **3** in a single loop to save some extra memory and lines of code. The key is to:

a. put the results of subproblem **1** in `results` instead of `left` (since it will later on be reused to contain the actual results)
a. have an aggregate variable that keeps the running product of nums on the right so far

```python
right = 1
for i in n - 1...0:
    results[i] = results[i] * right # subproblem 3
    right = right * nums[i]         # subproblem 2    
```
