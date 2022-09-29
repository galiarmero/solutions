import java.util.PriorityQueue;

public class Solution {
    public int minimumSumAfterReductions(int[] arr, int k) {
        if (arr.length == 0) return 0;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(arr.length, (x, y) -> y - x);
        for (int i = 0; i < arr.length; i++) {
            maxHeap.add(arr[i]);
        }

        while (k > 0) {
            maxHeap.add(maxHeap.poll() / 2);
            k--;
        }

        int sum = 0;
        while (!maxHeap.isEmpty()) {
            sum += maxHeap.poll();
        }
        return sum;
    }

    public static void main(String args[]) {
        Solution s = new Solution();
        assert s.minimumSumAfterReductions(new int[]{20, 7, 5, 4}, 3) == 18;
        assert s.minimumSumAfterReductions(new int[]{10, 20, 7}, 4) == 14;
    }
}
