---
categories: string,array
---

# [Valid Anagram](https://leetcode.com/problems/valid-anagram/)

One efficient way to programmatically identify if a string `t` is an anagram of string `s` is to check if these criteria are met:

1. They are of the same length
2. For each possible character, the number of occurences in `t` is the same as in `s`

To execute number **2** in linear time, we can use an approach akin to bank accounts. We create an _account_, then _deposit_ each character in `s` and _withdraw_ each character in `t`. In the end, if they are anagrams of each other, we should see in the _account_ that they cancel each other out.

This approach utilizes a _table_ that keeps track of the number of occurences of each character. Then, for each character `c` found in `s`, we increment the count of `c` in the table. Conversely, for each character `d` in `t`, we decrement the count of `d` in the table. To check if they are valid anagrams, each count in the table should be zero.

## Data Structure Choice

The choice of how we will represent the _table_ is a factor in the execution time.

A hash map, with the alphabet characters as keys and the occurrence counts as values, would suffice. However, maps tend to require more memory. Read and insert/update operations, although constant time, are also a little slower compared to plain arrays due to it's key-value nature.

An array offers a bit more faster solution. We won't really need to know the characters -- we just need to check the counts for each -- so having the alphabet keys is not necessary. Instead, we can use the position of each character in the alphabet (`c - 'a'`) as index and use an array of integers.
