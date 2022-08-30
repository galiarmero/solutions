class Solution {
    public boolean canJump(int[] nums) {
        int jumpsRemaining = nums[0];

        int i = 1;
        while (i < nums.length) {
            // If we don't have jumps remaining, give up
            // since the last index won't be reachable
            if (jumpsRemaining == 0) return false;

            // We spent 1 jump to reach this index. Subtract that from `jumpsRemaining`
            // Or if `nums[i]` is bigger, we'll use that instead
            jumpsRemaining = Math.max(jumpsRemaining - 1, nums[i]);
            i++;
        }

        return true;
    }

    public static void main(String args[]) {
        Solution s = new Solution();
        assert s.canJump(new int[]{2,3,1,1,4});
        assert !s.canJump(new int[]{3,2,1,0,4});
        assert !s.canJump(new int[]{0,2,3});
        assert s.canJump(new int[]{0});
    }
}
