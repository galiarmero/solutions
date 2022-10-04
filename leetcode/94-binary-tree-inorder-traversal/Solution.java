import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> values = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // Go left always and push the nodes along the way
            // until we arrive at the leftmost leaf
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            // The left child must have been visited already
            // Backtrack to the parent node, push it and then go to its right
            current = stack.pop();
            values.add(current.val);
            current = current.right;
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
        List<Integer> res1 = s.inorderTraversal(a);
        assert res1.size() == 3;
        assert res1.get(0) == 1;
        assert res1.get(1) == 3;
        assert res1.get(2) == 2;

        // 2
        TreeNode b = new TreeNode(1);
        List<Integer> res2 = s.inorderTraversal(b);
        assert res2.size() == 1;
        assert res2.get(0) == 1;

        // 3
        assert s.inorderTraversal(null).size() == 0;
    }
}
