import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> level = new ArrayDeque<>();
        level.offer(root);

        while (!level.isEmpty()) {
            int breadth = level.size();
            List<Integer> levelValues = new ArrayList<>();
            for (int i = 0; i < breadth; i++) {
                TreeNode node = level.poll();
                levelValues.add(node.val);

                if (node.left != null) {
                    level.offer(node.left);
                }
                if (node.right != null) {
                    level.offer(node.right);
                }
            }
            result.add(levelValues);
        }

        return result;
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
        List<List<Integer>> res1 = s.levelOrder(a);
        assert res1.size() == 3;
        assert res1.get(0).size() == 1;
        assert res1.get(1).size() == 2;
        assert res1.get(2).size() == 2;

        // 2
        TreeNode b = new TreeNode(1);
        List<List<Integer>> res2 = s.levelOrder(b);
        assert res2.size() == 1;
        assert res2.get(0).size() == 1;

        // 3
        TreeNode c = null;
        List<List<Integer>> res3 = s.levelOrder(c);
        assert res3.isEmpty();
    }
}
