import java.util.ArrayList;
import java.util.List;

public class SolutionRecursive {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        postorder(root, values);
        return values;
    }

    private void postorder(TreeNode node, List<Integer> values) {
        if (node == null) return;
        postorder(node.left, values);
        postorder(node.right, values);
        values.add(node.val);
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
