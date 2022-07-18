class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 1) return 0;

        int cheapest = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - cheapest > maxProfit) {
                maxProfit = prices[i] - cheapest;
            }

            if (prices[i] < cheapest) {
                cheapest = prices[i];
            }
        }

        return maxProfit;
    }

    public static void main(String args[]) {
        Solution s = new Solution();

        System.out.println(s.maxProfit(new int[] {7,1,5,3,6,4}));
        System.out.println(s.maxProfit(new int[] {7,6,4,3,1}));
        System.out.println(s.maxProfit(new int[] {7,1,5,3,6,4,0,3,8}));
        System.out.println(s.maxProfit(new int[] {1,3}));
        System.out.println(s.maxProfit(new int[] {3,1}));
    }
}