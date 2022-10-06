import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SolutionRecursive {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> values = new ArrayList<>();
        addToLevel(root, 0, values);
        return values;
    }

    private void addToLevel(Node node, int level, List<List<Integer>> values) {
        if (node == null) return;
        if (level == values.size()) {
            values.add(new ArrayList<>());
        }
        values.get(level).add(node.val);

        if (node.children != null) {
            for (Node child : node.children) {
                addToLevel(child, level + 1, values);
            }
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

        assert s.levelOrder(a).equals(Arrays.asList(
            Arrays.asList(1),
            Arrays.asList(3, 2, 4),
            Arrays.asList(5, 6)
        ));
    }
}
