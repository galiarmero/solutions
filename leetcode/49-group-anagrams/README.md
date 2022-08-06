---
categories: string,array,map
---

# [Group Anagrams](https://leetcode.com/problems/group-anagrams/)

We know that two strings are anagrams if:

1. They have the same set of characters
2. The number of occurrences for each of these characters is the same

Now, to group anagrams together, it would be great if we can put each string through a function that enforces the above conditions and generates a key representing the "anagram group" where it belongs.

Here are two possible methods:

- A sorted version of the string. Sorting two anagrams would result in the same string.
- A sequence representing the number of occurrences of each letter in the alphabet. How are we going to do this?

    We can generate a 26-character string of digits, representing the number of occurrences of each letter from a-z. For example, calculating string `"dabbed"` would generate this sequence: `"12021000000000000000000000"`. But, what if the number of occurrences reach double digits? That would mess up this method. A possible workaround is to put delimiters, say `","`, between each count.

    A simpler solution is to take advantage of the fact that integers have character equivalents in the ASCII table. We can form a sequence of 26 characters, representing the ASCII equivalent of the number of occurrences of a-z.

Once we are able to generate a sequence identifying the "anagram group" of a string, we can group the inputs together using a hash map, using the sequence as key and the list of strings that fall under the group as the value.
