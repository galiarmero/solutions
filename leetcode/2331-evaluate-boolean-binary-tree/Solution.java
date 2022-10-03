
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public boolean evaluateTree(TreeNode root) {
        if (root.val == 0) return false;
        if (root.val == 1) return true;
        if (root.val == 2)
            return evaluateTree(root.left) || evaluateTree(root.right);

        return evaluateTree(root.left) && evaluateTree(root.right);
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        // 1
        TreeNode a = new TreeNode(2,
            new TreeNode(1),
            new TreeNode(3,
                new TreeNode(0),
                new TreeNode(1)
            )
        );

        assert s.evaluateTree(a);

        // 2
        TreeNode b = new TreeNode(0);
        assert !s.evaluateTree(b);
    }
}
