import java.util.Arrays;

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;

        int midpoint = nums.length / 2;
        TreeNode node = new TreeNode(nums[midpoint]);
        node.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, midpoint));
        node.right = sortedArrayToBST(Arrays.copyOfRange(nums, midpoint+1, nums.length));
        return node;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        // 1
        TreeNode aExpected = new TreeNode(0,
            new TreeNode(-3,
                new TreeNode(-10),
                null
            ),
            new TreeNode(9,
                new TreeNode(5),
                null
            )
        );
        assert s.sortedArrayToBST(new int[]{-10,-3,0,5,9}).equals(aExpected);
    }
}
