---
categories: array,string
---

# [Brackets](https://www.codechef.com/problems/BRACKETS)

We are basically asked here to generate a shortened version of a balanced, valid parantheses sequence, while keeping the same _maximal balance_ of the sequence.

First, let's define maximal balance. It's basically just the length of the longest consecutive series of `(` in a valid parentheses sequence. In this example, the maximal balance is **3**:


> ()**(((**)()))

The algorithm to calculate the maximal balance is already provided in the problem and should help us better understand what it's looking for. Here's a pseudocode:

```py
getMaxBalance(sequence) {
    maxBalance = 0;
    balance = 0;
    for each c in sequence:
        if c is '('
            balance = balance + 1
        else if c is ')'
            balance = balance - 1

        maxBalance = max(maxBalance, balance)

    return maxBalance
```

Now that we can calculate `maximalBalance`, the most optimal sequence is really just composed of `'(' * maximalBalance` followed by `')' * maximalBalance`. If `maximalBalance` is **3**, it should look like this:

> ((()))

This is shorter, with no opening parenthesis `'('` that follow after closing ones `')'`, while keeping the maximal balance intact.
