---
categories: array
---

# [Broken Telephone](https://www.codechef.com/problems/BROKPHON)

We are essentially given an array of integer `messages` of length `N` representing the messages received by each player in a message relay game. The task is to count how many players misheard the message or whispered it incorrectly. In other words, we need to count the players at fault!

Essentially, a player `i` is at fault if:

- `messages[i] != messages[i - 1]`: it misheard the message
- `messages[i] != messages[i + 1]`: it whispered incorrectly

To solve, iterate over each message and count the player if at least one of the above conditions are true. Even if both are true, we only count the player once. Be careful not to access outside the bounds of `messages` when checking for the first and last player!
