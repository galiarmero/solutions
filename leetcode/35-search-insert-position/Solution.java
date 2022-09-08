class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length;
        int midpoint;

        while (left < right) {
            midpoint = left + (right - left) / 2;

            if (nums[midpoint] >= target)
                right = midpoint;
            else
                left = midpoint + 1;
        }

        return left;
    }

    public static void main(String args[]) {
        Solution s = new Solution();
        assert s.searchInsert(new int[]{1, 3, 5, 6}, 5) == 2;
        assert s.searchInsert(new int[]{1, 3, 5, 6}, 2) == 1;
        assert s.searchInsert(new int[]{1, 3, 5, 6}, 7) == 4;
        assert s.searchInsert(new int[]{1, 3, 5, 6}, 0) == 0;
        assert s.searchInsert(new int[]{1, 3, 5, 6, 8}, 3) == 1;
        assert s.searchInsert(new int[]{1, 3, 5, 6, 8}, 4) == 2;
        assert s.searchInsert(new int[]{1, 3, 5, 6, 8}, 8) == 4;
        assert s.searchInsert(new int[]{1, 3, 5, 6, 8}, 0) == 0;
    }
}
