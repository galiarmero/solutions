import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public int findKthLargestMaxHeap(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(nums.length, (a, b) -> b - a);
        for (int num : nums) {
            maxHeap.offer(num);
        }

        int kthLargest = 0;
        while (k-- > 0) {
            kthLargest = maxHeap.poll();
        }
        return kthLargest;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        assert s.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2) == 5;
        assert s.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4) == 4;
    }
}
