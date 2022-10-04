import java.util.ArrayList;
import java.util.List;

public class SolutionRecursive {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        inorder(root, values);
        return values;
    }

    private void inorder(TreeNode node, List<Integer> values) {
        if (node == null) return;
        inorder(node.left, values);
        values.add(node.val);
        inorder(node.right, values);
    }

    public static void main(String[] args) {
        SolutionRecursive s = new SolutionRecursive();

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
