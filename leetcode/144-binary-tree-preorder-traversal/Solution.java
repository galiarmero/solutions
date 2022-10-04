import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        if (root == null) return values;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            values.add(node.val);

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return values;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        // 1
        TreeNode a = new TreeNode(1,
            null,
            new TreeNode(2,
                new TreeNode(3),
                null
            )
        );
        List<Integer> res1 = s.preorderTraversal(a);
        assert res1.size() == 3;
        assert res1.get(0) == 1;
        assert res1.get(1) == 2;
        assert res1.get(2) == 3;

        // 2
        TreeNode b = new TreeNode(1);
        List<Integer> res2 = s.preorderTraversal(b);
        assert res2.size() == 1;
        assert res2.get(0) == 1;

        // 3
        assert s.preorderTraversal(null).size() == 0;
    }
}
