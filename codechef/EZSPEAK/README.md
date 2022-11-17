---
categories: array,string
---

# [Easy Pronunciation](https://www.codechef.com/problems/EZSPEAK)

Maintain a set of `vowels`, both lowercase and uppercase. Then, iterate over each character in the word and count the consecutive consonants (i.e. characters is not in `vowels`). Reset the count once a vowel is encountered.

If the consecutive consonants reached `4`, then it's not easy to pronounce.
