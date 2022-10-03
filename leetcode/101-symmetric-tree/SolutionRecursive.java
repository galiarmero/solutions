class SolutionRecursive {
    public boolean isSymmetric(TreeNode root) {
        return (root == null || isSymmetric(root.left, root.right));
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null || right == null) return left == right;
        if (left.val != right.val) return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    public static void main(String[] args) {
        SolutionRecursive s = new SolutionRecursive();
        TreeNode tree = new TreeNode(1,
            new TreeNode(2,
                new TreeNode(3,
                    new TreeNode(5),
                    new TreeNode(6,
                        new TreeNode(9),
                        null
                    )
                ),
                new TreeNode(4,
                    new TreeNode(7),
                    new TreeNode(8)
                )
            ),
            new TreeNode(2,
                new TreeNode(4,
                    new TreeNode(8),
                    new TreeNode(7)
                ),
                new TreeNode(3,
                    new TreeNode(6,
                        null,
                        new TreeNode(9)
                    ),
                    new TreeNode(5)
                )
            )
        );

        assert s.isSymmetric(tree);
    }
}
