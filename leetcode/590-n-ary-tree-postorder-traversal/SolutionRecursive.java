import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SolutionRecursive {
    public List<Integer> postorder(Node root) {
        List<Integer> values = new ArrayList<>();
        postorder(root, values);
        return values;
    }

    private void postorder(Node node, List<Integer> values) {
        if (node == null) return;
        if (node.children != null) {
            for (Node child : node.children) {
                postorder(child, values);
            }
        }
        values.add(node.val);
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

        assert s.postorder(a).equals(Arrays.asList(5, 6, 3, 2, 4, 1));
    }
}
