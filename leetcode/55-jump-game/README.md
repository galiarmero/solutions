---
categories: array,greedy,dynamic programming
---

# [Jump Game](https://leetcode.com/problems/jump-game/)

It is possible to determine whether we can reach the last index in linear time.

For each index from `0` to `n - 1`, the key is to ask every time: _"Do I have jumps remaining to reach this index?"_. If I have jumps remaining, I can reach **this** index. Otherwise, I can't.

By asking that question, we have identified the subproblem to work on in order to solve the bigger problem. That subproblem involves:

1. Checking if we have jumps remaining from the previous iteration
2. Calculating how many possible jumps are remaining at **this** index, using that information to tackle the same subproblem at the **next** iteration until we reach the last.

We start by setting the initial `jumpsRemaining`. `nums[0]` tells us the initial jump count to start with.

```java
int jumpsRemaining = nums[0];
```

Then, with index `i` from `1` up to `n - 1`, we tackle the subproblem:

Of course, if we no longer have jumps remaining, the last index is impossible to reach. We give up and return `false`.

```java
int jumpsRemaining = nums[0];

int i = 1;
while (i < nums.length) {
    // If we don't have jumps remaining, give up
    // since the last index won't be reachable
    if (jumpsRemaining == 0) return false;
}
```

Otherwise, we calculate how many jumps we have left at this point. We have spent `1` jump to reach this index from the previous. So we deduct that from `jumpsRemaining`. But if `nums[i]` is bigger, we use it instead. We are greedy! The more jumps we have remaining, the likelier we'll reach the last index.

```java
int jumpsRemaining = nums[0];

int i = 1;
while (i < nums.length) {
    // If we don't have jumps remaining, give up
    // since the last index won't be reachable
    if (jumpsRemaining == 0) return false;

    // We spent 1 jump to reach this index. Subtract that from `jumpsRemaining`
    // Or if `nums[i]` is bigger, we'll use that instead
    jumpsRemaining = Math.max(jumpsRemaining - 1, nums[i]);
    i++;
}
```

If after tackling all these subproblems one by one, we reach the last index (`n - 1`) with at least one jump remaining, then we made it!

```java
int jumpsRemaining = nums[0];

int i = 1;
while (i < nums.length) {
    // If we don't have jumps remaining, give up
    // since the last index won't be reachable
    if (jumpsRemaining == 0) return false;

    // We spent 1 jump to reach this index. Subtract that from `jumpsRemaining`
    // Or if `nums[i]` is bigger, we'll use that instead
    jumpsRemaining = Math.max(jumpsRemaining - 1, nums[i]);
    i++;
}

return true;
```
