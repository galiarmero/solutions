import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int depth = 0;

        while (!q.isEmpty()) {
            int breadth = q.size();
            depth++;
            for (int i = 0; i < breadth; i++) {
                TreeNode node = q.poll();

                if (node.left == null && node.right == null) {
                    return depth;
                }

                if (node.left != null) {
                    q.offer(node.left);
                }
                
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
        return depth;
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

        // 5
        TreeNode d = new TreeNode(1,
            new TreeNode(2,
                new TreeNode(4),
                new TreeNode(5)
            ),
            new TreeNode(3)
        );
        assert s.minDepth(d) == 2;
    }
}
