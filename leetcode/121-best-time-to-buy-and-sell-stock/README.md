---
topics: array
---

# [Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)

We are given a list of daily stock prices. The task is to identify -- in retrospect -- what our maximum profit would be had we picked the _most_ perfect days to buy and sell it.

It is possible to determine the maximum profit in a single loop, thinking of each iteration as a single 'day' to examine the stock price. As we go through each day's stock price, we have to keep track of two things:

- `cheapestPrice` - the cheapest price we have encountered thus far. The best time, thus far, to buy the stock is at this price.
- `maxProfit` - the maximum profit we could possibly earn thus far. A day's profit can be calculated by subtracting the `cheapestPrice` from the day's stock price (`prices[i]`). If the day's profit is bigger than the `maxProfit` we have thus far, then we update `maxProfit` with the former.

At each iteration, given that day's price (`prices[i]`), the task is to determine the new `cheapestPrice` and `maxProfit`, keeping old values if no new values fit the criteria.

_What happens when we encounter a new `cheapestPrice`?_ Well and good. We'll proceed with the loop and see if there's an opportunity for better profit in the coming days. If there's none, we'll stick with the `maxProfit` that we determined previously.
