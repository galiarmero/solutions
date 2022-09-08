import java.util.stream.IntStream;

class Solution {
    public int shipWithinDays(int[] weights, int days) {
        // These appear to run a bit slower than using a simple loop
        // int left = IntStream.of(weights).max().getAsInt();
        // int right = IntStream.of(weights).sum();
        int left = max(weights);
        int right = sum(weights);
        int midpoint;

        while (left < right) {
            midpoint = left + (right - left) / 2;
            if (isPossibleWithCapacity(midpoint, weights, days))
                right = midpoint;
            else
                left = midpoint + 1;
        }

        return left;
    }

    public int shipWithinDaysLinear(int[] weights, int days) {
        int left = IntStream.of(weights).max().getAsInt();
        int right = IntStream.of(weights).sum();

        while (left <= right) {
            if (isPossibleWithCapacity(left, weights, days)) return left;
            left++;
        }

        return -1;
    }

    private boolean isPossibleWithCapacity(int capacity, int[] weights, int targetDays) {
        int days = 1, total = 0;

        for (int weight : weights) {
            total += weight;
            if (total > capacity) { // Exceeds capacity, ship it the next day
                total = weight;
                days++;

                if (days > targetDays) return false;
            }
        }

        return true;
    }

    private int max(int[] arr) {
        int max = 0;
        for (int i : arr) {
            if (i > max) max = i;
        }
        return max;
    }

    private int sum(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return sum;
    }

    public static void main(String args[]) {
        Solution s = new Solution();
        assert s.shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10}, 5) == 15;
        assert s.shipWithinDays(new int[]{3,2,2,4,1,4}, 3) == 6;
        assert s.shipWithinDays(new int[]{1,2,3,1,1}, 4) == 3;
    }
}
