import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isBalanced(TreeNode node) {
        if (node == null) return true;
        return abs(getDepth(node.left) - getDepth(node.right)) <= 1
                && (isBalanced(node.left)
                && isBalanced(node.right));
    }

    private int getDepth(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(getDepth(node.left), getDepth(node.right));
    }

    private int abs(int x) {
        return (x < 0) ? x * -1 : x;
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
        assert s.isBalanced(a);

        // 2
        TreeNode b = new TreeNode(1,
            new TreeNode(2,
                new TreeNode(3,
                    new TreeNode(4),
                    new TreeNode(4)
                ),
                new TreeNode(3)
            ),
            new TreeNode(2)
        );
        assert !s.isBalanced(b);

        // 3
        assert s.isBalanced(null);
    }
}
