import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SolutionRecursive {
    public List<Integer> preorder(Node root) {
        List<Integer> values = new ArrayList<>();
        preorder(root, values);
        return values;
    }

    private void preorder(Node node, List<Integer> values) {
        if (node == null) return;
        values.add(node.val);

        if (node.children == null) return;
        for (Node child : node.children) {
            preorder(child, values);
        }
    }

    public static void main(String[] args) {
        SolutionRecursive s = new SolutionRecursive();
        
        // 1
        Node a = new Node(1, Arrays.asList(
            new Node(3, Arrays.asList(
                new Node(5),
                new Node(6)
            )),
            new Node(2),
            new Node(4)
        ));

        assert s.preorder(a).equals(Arrays.asList(1, 3, 5, 6, 2, 4));
    }
}
