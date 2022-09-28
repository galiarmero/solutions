class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int lo = 1;
        int hi = max(piles);
        int midpoint;

        while (lo < hi) {
            midpoint =  (hi - lo) / 2 + lo;

            if (canFinish(piles, h, midpoint)) {
                hi = midpoint;
            } else {
                lo = midpoint + 1;
            }
        }

        return lo;
    }

    private boolean canFinish(int[] piles, int targetHours, int bananasPerHour) {
        int hoursSpent = 0;
        for (int p : piles) {
            // Calculate how many hours it will take to finish this pile considering the bananasPerHour
            // This is just `Math.ceil(p / bananasPerHour)` but way faster
            hoursSpent += (p + bananasPerHour - 1) / bananasPerHour;
            if (hoursSpent > targetHours) {
                return false;
            }
        }
        return hoursSpent <= targetHours;
    }

    private int max(int[] piles) {
        int max = 0;
        for (int p : piles) {
            if (p > max) max = p;
        }
        return max;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        assert s.minEatingSpeed(new int[]{3, 6, 7, 11}, 8) == 4;
        assert s.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5) == 30;
        assert s.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6) == 23;
    }
}
