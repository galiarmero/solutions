class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        // 1
        TreeNode a = new TreeNode(3,
            new TreeNode(9),
            new TreeNode(20,
                new TreeNode(15),
                new TreeNode(7)
            )
        );
        System.out.println(s.maxDepth(a));
        assert s.maxDepth(a) == 3;

        // 2
        TreeNode b = new TreeNode(2,
            null,
            new TreeNode(3,
                null,
                new TreeNode(4,
                    null,
                    new TreeNode(5,
                        null,
                        new TreeNode(6)
                    )
                )
            )
        );
        System.out.println(s.maxDepth(b));
        assert s.maxDepth(b) == 5;

        // 3
        TreeNode c = new TreeNode(2);
        assert s.maxDepth(c) == 1;

        // 4
        assert s.maxDepth(null) == 0;

        // 5
        TreeNode d = new TreeNode(1,
            new TreeNode(2,
                new TreeNode(4),
                new TreeNode(5)
            ),
            new TreeNode(3)
        );
        assert s.maxDepth(d) == 3;

        // 6
        TreeNode e = new TreeNode(1,
            null,
            new TreeNode(2)
        );
        assert s.maxDepth(e) == 2;
    }
}
