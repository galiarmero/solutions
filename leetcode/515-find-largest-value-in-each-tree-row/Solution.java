import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;


class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> largest = new ArrayList<>();
        if (root == null) return largest;

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int breadth = q.size();
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < breadth; i++) {
                TreeNode node = q.poll();
                if (node.val > max) {
                    max = node.val;
                }

                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            largest.add(max);
        }
        return largest;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        // 1
        TreeNode a = new TreeNode(3,
            new TreeNode(9,
                new TreeNode(19),
                null
            ),
            new TreeNode(20,
                new TreeNode(15),
                new TreeNode(7)
            )
        );
        assert s.largestValues(a).equals(Arrays.asList(3, 20, 19));

        // 2
        TreeNode b = new TreeNode(1,
            new TreeNode(2),
            new TreeNode(3)
        );
        assert s.largestValues(b).equals(Arrays.asList(1, 3));

        // 3
        assert s.largestValues(null).isEmpty();
    }
}
