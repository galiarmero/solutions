class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int profit = 0;
        int buyPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - buyPrice > profit) {
                profit = prices[i] - buyPrice;
                continue;
            }

            maxProfit += profit;
            profit = 0;
            buyPrice = prices[i];
        }

        return maxProfit + profit;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        assert s.maxProfit(new int[]{7, 1, 2, 5, 3, 6}) == 7;
        assert s.maxProfit(new int[]{7, 1, 2, 5, 6, 7}) == 6;
        assert s.maxProfit(new int[]{7, 1, 2, 5, 3, 6, 1}) == 7;
        assert s.maxProfit(new int[]{7, 1, 2, 5, 3, 6, 6, 1}) == 7;
        assert s.maxProfit(new int[]{7, 6, 5, 4, 2}) == 0;
    }
}
