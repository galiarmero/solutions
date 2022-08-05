import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        int minCoinCount[] = new int[amount + 1];
        minCoinCount[0] = 0;
        final int MAX_COIN_COUNT = amount + 1;

        // Calculate the minimum coin count for amounts starting from 1 up to `amount`
        for (int a = 1; a <= amount; a++) {
            // Initilize with an impossibly high coin count (greater than `amount`)
            minCoinCount[a] = MAX_COIN_COUNT;

            // For each coin denomination...
            for (int c : coins) {
                // If the current amount (`a`) is less than this coin,
                // we can't use this coin as "change" for `a`. So we skip.
                if (a < c) continue;

                // Otherwise, we calculate the most optimal coin count
                // to make up the amount (`a`)
                minCoinCount[a] =
                    Math.min(
                        minCoinCount[a],        // the current minimum count to make up `a`
                        1 + minCoinCount[a - c] // 1 of this coin (`c`) + the minimum needed to make up `a - c`
                    );
            }
        }

        if (minCoinCount[amount] > amount) return -1;
        return minCoinCount[amount];
    }

    public static void main(String args[]) {
        Solution s = new Solution();
        // System.out.println(s.coinChange(new int[]{ 1, 2, 5 }, 11));
        // System.out.println(s.coinChange(new int[]{ 2 }, 3));
        // System.out.println(s.coinChange(new int[]{ 1 }, 0));
        // System.out.println(s.coinChange(new int[]{ 1, 2, 6, 8 }, 9));
        System.out.println(s.coinChange(new int[]{ 186, 419, 83, 408 }, 6249));
        System.out.println(s.coinChange(new int[]{ 1, 3, 4, 5 }, 7));
    }
}
