public class TreeNode {
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

    public boolean equals(TreeNode other) {
        if (other == null) return false;
        if (this.val != other.val) return false;

        boolean isLeftEqual =
            ((this.left == null || other.left == null) && this.left == other.left) ||
            this.left.equals(other.left);

        boolean isRightEqual =
            ((this.right == null || other.right == null) && this.right == other.right) ||
            this.right.equals(other.right);

        return isLeftEqual && isRightEqual;
    }
}
