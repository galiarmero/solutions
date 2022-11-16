---
categories: linked list,math
---

# [Add Two Numbers](https://leetcode.com/problems/add-two-numbers/)

We are given two linked lists, each representing non-negative integers. Each linked list has nodes containing the digits of the integer, arranged in reverse order. The task is two add the two lists and return the sum, which is also represented as a linked list of digits in reverse order.

The solution is basically doing multi-digit addition on paper the way we were taught back in grade school. It's adding digits in every place value -- starting from the ones place -- and "carrying over" an extra `1` to the next higher place value if the sum >= 10. The lists are already in reverse so we can easily start from the ones place.

Given `l1` and `l2` as pointers to the current node (digit) in each list and a `carry` value that is initially `0`, to get the sum of the two digits in the same place value:

```py
sum = l1.value if l1 is not null else 0
sum += l2.value if l2 is not null else 0
sum += carry
```

Once we have the sum, we extract the digit and store it at the tail of the resulting list. We also compute for the `carry` if the sum exceeds `10`.

```py
tail.next = new ListNode(sum % 10) # the last digit of the sum
tail = tail.next
carry = sum / 10 # integer division, the value to be carried over, either 1 or 0
```

Don't forget to point `l1` and `l2` to the next value or make it null if it's the end of the list. Keep doing the same steps until `l1` is null, `l2` is null, and `carry` is already `0`. Then return the result which is the pointer to the head of the list.

See [Solution.java](./Solution.java) for the complete source code.
