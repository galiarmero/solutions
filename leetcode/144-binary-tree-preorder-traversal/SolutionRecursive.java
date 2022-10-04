import java.util.ArrayList;
import java.util.List;

class SolutionRecursive {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        preorder(root, values);
        return values;
    }

    public void preorder(TreeNode node, List<Integer> values) {
        if (node == null) return;
        values.add(node.val);
        preorder(node.left, values);
        preorder(node.right, values);
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
