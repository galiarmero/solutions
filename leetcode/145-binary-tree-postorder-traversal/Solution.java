import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> values = new LinkedList<>();
        if (root == null) return values;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            values.addFirst(current.val);

            if (current.left != null) {
                stack.push(current.left);
            }

            if (current.right != null) {
                stack.push(current.right);
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
        List<Integer> res1 = s.postorderTraversal(a);
        assert res1.size() == 3;
        assert res1.get(0) == 3;
        assert res1.get(1) == 2;
        assert res1.get(2) == 1;

        // 2
        TreeNode b = new TreeNode(1);
        List<Integer> res2 = s.postorderTraversal(b);
        assert res2.size() == 1;
        assert res2.get(0) == 1;

        // 3
        assert s.postorderTraversal(null).size() == 0;
    }
}
