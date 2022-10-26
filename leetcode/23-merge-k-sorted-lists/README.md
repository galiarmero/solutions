---
categories: linked list,heap,priority queue
---

# [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/)

The task is to merge already-sorted linked lists into a single linked list and maintain order.

Since each list is sorted, we will always be certain that:

- The first node of the resulting linked list is the one with the minimmum value among the the first nodes of each list
- The second node of the resulting linked list is the minimum among:
  a. the next node of the first node (in its original list), or
  b. one of the remaining first nodes in each list

The same idea applies in building the third node, and so on. At each step, we pick the minimum among the "candidate" nodes. And to do that, we need to maintain a collection of candidate nodes in the first place. Both can actually be achieved using a _min heap_. This is a collection that always churns out the minimum among its elements.

In Java, we can use a `PriorityQueue` with a comparator that prioritizes the node with the minimum value.

```java
PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
```

Let's take a look at this example. We have 3 lists:

|   |   |   |
| - | - | - |
| 1 | 4 | 5 |
| 1 | 3 | 4 |
| 2 | 6 |


First, we can `offer` the first node of each linked list into the `minHeap`.

| `minHeap` |
| 1 |
| 1 |
| 2 |


Then, as long as the `minHeap` is not empty, we can start appending what we `poll` out of it (i.e. the minimum among the candidates) to the tail of the resulting linked list.

| `result` |
| 1 |


For every `poll`, we need to `offer` back to it the next node of what we polled in its original list.

|   |   |   |
| - | - | - |
| ~~1~~ | *4* | 5 |
| 1 | 3 | 4 |
| 2 | 6 |

In this case, we offer _4_ which is next to _1_ in the first list:

| `minHeap` |
| 1 |
| 2 |
| *4* |

We `poll` again and get another _1_. 

| `result` |
| 1 | 1 |

But this time, the next node of _1_ in its original list is _3_.

|   |   |   |
| - | - | - |
| ~~1~~ | 4 | 5 |
| ~~1~~ | *3* | 4 |
| 2 | 6 |

We push that into the `minHeap`.

| `minHeap` |
| 2 |
| *3* |
| 4 |

We `poll` the `minHeap` again and get _2_:

| `result` |
| 1 | 1 | 2 |

That _2_ was from the third list.

|   |   |   |
| - | - | - |
| ~~1~~ | 4 | 5 |
| ~~1~~ | 3 | 4 |
| ~~2~~ | *6* |

We `offer` _6_, the next node in the third list, back to the `minHeap`:

| `minHeap` |
| 3 |
| 4 |
| 6 |

At this point, I hope you get the drill. We repeat the steps until the `minHeap` is empty. That means we are no longer able to replenish what we `poll` from the `minHeap` since the element is the last one in its respective list. Thus, we return the `result`.


## Reminders

- The `.next` value of the last nodes will be null. Make sure you don't put them into the `minHeap`
- The `result` should actually be a pointer to the first node in the resulting list. Check out my [Solution](Solution.java) if you want to see how my approach in building the result.


