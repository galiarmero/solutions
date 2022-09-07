---
categories: binary search
---

# [First Bad Version](https://leetcode.com/problems/first-bad-version/)

The task is given `n` as the number of versions `[1, 2, ..., n]` and a predicate `boolean isBadVersion(version)` which determines whether a version is bad, we should identify the first bad version.

Let's use `n = 6` as an example. We get this sequence of versions:

| 1 | 2 | 3 | 4 | 5 | 6 |
| - | - | - | - | - | - |

Let's say applying the predicate `isBadVersion` to each `version` would generate us these answers:

| 1 | 2 | 3 | 4 | 5 | 6 |
| - | - | - | - | - | - |
| no | no | no | yes | yes | yes |

What would be the first bad version? It would be easy using linear search but that would take a long time given a large `n`.

Using binary search would be more optimal since we narrow down the sequence of items we need to search into half at each iteration. There is an excellent [Topcoder article](https://www.topcoder.com/thrive/articles/Binary%20Search) discussing the main theorem and the kinds of problems binary search can potentially solve, not just the basic problems requiring to find the index of an item in a sorted sequence.

Taking some pointers from that article, we'll arrive at this pseudocode to find the first bad version:

```python
binary_search(n):
    left = 1, right = n
    while left < right:
        mid = left + (right - left) / 2
        if isBadVersion(mid):
            right = mid
        else:
            left = mid + 1

    if p(left) == false:
        complain # isBadVersion(x) is false for all x in S! (This won't happen)

    return left # left is the least x for which isBadVersion(x) is true
```

Let's test this out with our example, starting with the initial _search space_: all the versions. 

```python
left = 1
right = 6
```

| 1 | 2 | 3 | 4 | 5 | 6 |
| - | - | - | - | - | - |
| no | no | **no** | yes | yes | yes |

`mid` would be `3`. But that's **not** a bad version. So we ignore left portion of the search space and move the `left` to `mid + 1`.

```python
mid = 3
left = 4
right = 6
```

| 4 | 5 | 6 |
| - | - | - |
| yes | **yes** | yes |

`mid` is `5`. That's a bad version! Surely, either it is the first bad version or the bad version is on its left. The item(s) on its right are surely not the first bad version, right? So let's cut the right side by making `right` equal to `mid`.


```python
mid = 5
left = 4
right = 5
```

| 4 | 5 |
| - | - |
| **yes** | yes |

`mid` is `4`. That's another bad version. So we set that again as the `right`.

```python
mid = 4
left = 4
right = 4
```

| 4 |
| - |
| **yes** |

`left` is no longer less than `right`. That means we have narrowed the _search space_ down to a single element. That is our answer!
