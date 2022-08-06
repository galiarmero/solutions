---
categories: string,stack
---

# [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)

To determine if the brackets in the string are balanced and in the right order, we can take advantage of the last-in-first-out (LIFO) principle enforced by stacks.

Traversing the string linearly (`O(n)`), we check each character.

If the character is an opening bracket (`'('`, `'{'`, `'['`), we simply push it in the stack.

Otherwise, we are assured it's a closing bracket (`')'`, `'}'`, `']'`). That means we need to:

1. Check if the stack is empty. If it is, then there's no matching opening bracket for the current closing bracket. So, the string is not balanced.
2. Otherwise, _Pop_ (retrieve and remove) the character at the top of the stack
3. Check if the popped character is the matching opening bracket of the current closing bracket. If it they don't match, the string is not balanced.

There's one final check after traversing without finding any invalidities. If the stack still has dangling opening brackets hanging around inside it, that means it/they don't have a matching closing brackets so the string is not balanced. Otherwise, it is a balanced and valid string of brackets.
