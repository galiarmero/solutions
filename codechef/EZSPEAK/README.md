---
categories: array,string
---

# [Easy Pronunciation](https://www.codechef.com/problems/EZSPEAK)

Maintain a set of `vowels`, both lowercase and uppercase. Then, iterate over each character in the word and keep a count of consecutive consonants (i.e. characters is not in `vowels`). Reset the count once a vowel is encountered.

If the consecutive consonants count reached `4`, then it's not easy to pronounce.
