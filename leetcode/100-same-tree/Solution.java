class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) return p == q;
        if (p.val == q.val)
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        return false;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        // 1
        TreeNode a = new TreeNode(1,
            new TreeNode(2),
            new TreeNode(3)
        );
        TreeNode b = new TreeNode(1,
            new TreeNode(2),
            new TreeNode(3)
        );

        assert s.isSameTree(a, b);

        // 2
        TreeNode c = new TreeNode(1,
            new TreeNode(2),
            null
        );
        TreeNode d = new TreeNode(1,
            null,
            new TreeNode(2)
        );

        assert !s.isSameTree(c, d);

        // 3
        TreeNode e = new TreeNode(1,
            new TreeNode(2),
            new TreeNode(1)
        );
        TreeNode f = new TreeNode(1,
            new TreeNode(1),
            new TreeNode(2)
        );

        assert !s.isSameTree(e, f);
    }
}
