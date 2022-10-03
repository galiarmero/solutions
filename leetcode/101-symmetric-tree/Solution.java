import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root.left);
        nodeQueue.add(root.right);

        while (!nodeQueue.isEmpty()) {
            TreeNode left = nodeQueue.poll();
            TreeNode right = nodeQueue.poll();

            if (left == null || right == null) {
                if (left == right) continue;
                return false;
            }
            if (left.val != right.val) return false;

            // Outer nodes
            nodeQueue.add(left.left);
            nodeQueue.add(right.right);

            // Inner nodes
            nodeQueue.add(left.right);
            nodeQueue.add(right.left);
        }

        return true;
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
