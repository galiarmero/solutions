---
categories: array,dynamic programming
---

# [Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii)

We are given a list of daily stock prices. The task is to calculate -- in retrospect -- the maximum profit we can get out of it if we are allowed to buy and sell multiple times. We are only allowed to have one share at any time.

This can be calculated in linear time. As we go through each day's stock price, we have to keep track of these values:

- `buyPrice` - The price where we buy. It's the cheapest price we can find at the start or after selling
- `profit` - The profit we can have if we sell the stock we have currently
- `maxProfit` - The total profits we have by adding all the profits including previous ones

Initially, we can set `buyPrice = prices[0]`, and both `profit` and `maxProfit` to `0`. We then start at index `1` and examine the prices:

```python
for prices[1]...price[n - 1]:
    if prices[i] - buyPrice > profit:
        profit = prices[i] - buyPrice
    else
        maxProfit += profit
        profit = 0
        buyPrice = prices[i]

```

At each day `i`, if we are potentially making a `profit` (which is `price[i] - buyPrice`) that could be higher than yesterday's profit, then we update our `profit`. Otherwise, we should sell the previous day. So we cash in by adding `profit` to `maxProfit`, set `profit` to `0`, and buy today's price: `buyPrice = prices[i]`.

After checking each day, don't forget to add `profit` to `maxProfit`. This is for cases when we profitted but we finished iterating so we did not have the chance to add this profit to `maxProfit`.
