import java.util.Arrays;

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] results = new int[nums.length];

        // Calculate the product on the left of `nums[i]`
        // `nums[0]` has no left so we set it to 1
        results[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            results[i] = nums[i - 1] * results[i - 1];
        }

        // Then we simultaneously calculate
        // 1. What's on the right of `nums[i]` which is stored on `right`
        // 2. The final result for `i`. That is basically the product of:
        //      - the products on the left of nums[i] (which is already stored on `result[i]`)
        //      - and the products on the right of nums[i] (which is stored in `right`)
        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            results[i] *= right;
            right *= nums[i];
        }

        return results;
    }

    public static void main(String args[]) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.productExceptSelf(new int[]{1,2,3,4})));
    }
}
