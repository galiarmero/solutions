class SolutionDFS {
    public int minDepth(TreeNode node) {
        if (node == null) return 0;
        int leftDepth = minDepth(node.left);
        int rightDepth = minDepth(node.right);
        if (leftDepth == 0 || rightDepth == 0) {
            return leftDepth + rightDepth + 1;
        }
        return Math.min(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        SolutionDFS s = new SolutionDFS();

        // 1
        TreeNode a = new TreeNode(3,
            new TreeNode(9),
            new TreeNode(20,
                new TreeNode(15),
                new TreeNode(7)
            )
        );
        System.out.println(s.minDepth(a));
        assert s.minDepth(a) == 2;

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
        System.out.println(s.minDepth(b));
        assert s.minDepth(b) == 5;

        // 3
        TreeNode c = new TreeNode(2);
        assert s.minDepth(c) == 1;

        // 4
        assert s.minDepth(null) == 0;
    }
}
