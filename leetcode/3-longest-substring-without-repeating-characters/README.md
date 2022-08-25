---
categories: map,string
---

# [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

The ask is perfectly described in the title. It's probably easier to solve using the naked eye but the challenge is to come up with an _O(n)_ approach.

We can loop over each character `c` in string `s`, updating these three variables when certain conditions are met:

- `lastIndices` - keeps track of the index where a given character last appeared.
- `currentLength` - the length of the non-repeating substring we have built thus far
- `maxLength` - the length of the longest non-repeating substring we have encountered thus far


## An initial attempt

For each character `c`, we can try these considerations:

- If the character exists as a key in `lastIndices`, that means we have encountered this before. So we can reset `currentLength` to `1`
- Otherwise, we haven't encountered this character before. We just increment `currentLength` by `1`. Basically, we're saying: _Cool, this character is new. Let's count it in the 'non-repeating' substring we are building_
- Put `c` in `lastIndices` with the value `i`, the index of `c` in the string `s`
- If the `currentLength` is already longer than the `maxLength`, then this is the longest substring we encountered so far. Set `maxLength` to the `currentLength`.

Looks good? This works for the sample inputs: `abcabcbb`, `bbbbb`, `pwwkew`. But if you test it out in a string like `bbaabcd`, it will generate the incorrect result.

## The catch

In an input such as `bbaabcd`, the previous approach will yield an answer of `3`. That's because when we reach the third occurrence of `b`, we reset the `currentLength` to `1`, making the underlined as the longest substring: _bbaa<u>**b**cd</u>_.

But we know that the correct answer is `4`. We should have counted the second `a` as the start of the longest substring: _bba<u>**a**bcd</u>_.

That means, when we reached the third `b`, we should have continued counting by incrementing `currentLength` by 1 instead of resetting it to 1. That is because `b`'s last occurence was _before_ the start of the current substring, so it's not counted in the `currentLength` anyway.

`i - currentLength` is the index where we started the current substring. We can use that to determine whether we count the character in the `currentLength` or reset it and build a new current substring.

```java
lastIndex = lastIndices.get(c)
if (lastIndex < i - currentLength) {
    currentLength++;
} else {
    // So start a new current substring
}
```

The `else` part is when the character occurred in the current substring. Here, we basically cut a portion of `currentLength` and begin counting one position to the right of `lastIndex`. That is equivalent to resetting `currentLength` to `i - lastIndex`.

```java
lastIndex = lastIndices.get(c)
if (lastIndex < i - currentLength) {
    currentLength++;
} else {
    currentLength = i - lastIndex;
}
```


In summary, we have to tweak the considerations we laid out in the initial attempt. Here's a proper version:

- If the character exists as a key in `lastIndices`, that means we have encountered this before. Assign the value to `lastIndex`. It could go two ways here:
    - a. The `lastIndex` occurred before the current substring. It's not counted anyway so we just increment `currentLength` by `1`
    - b. Otherwise, we've encountered it right after we started the current substring. Trim `currentLength` by setting it to `i - lastIndex`.

- Otherwise, we haven't encountered this character before. We just increment `currentLength` by `1`. Basically, we're saying: _Cool, this character is new. Let's count it in the 'non-repeating' substring we are building_
- Put `c` in `lastIndices` with the value `i`, the index of `c` in the string `s`
- If the `currentLength` is already longer than the `maxLength`, then this is the longest substring we encountered so far. Set `maxLength` to the `currentLength`.
