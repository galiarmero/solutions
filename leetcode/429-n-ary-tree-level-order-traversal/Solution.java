import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> values = new ArrayList<>();
        if (root == null) return values;

        Queue<Node> nodesEncountered = new ArrayDeque<>();
        nodesEncountered.offer(root);

        while (!nodesEncountered.isEmpty()) {
            int breadth = nodesEncountered.size();
            List<Integer> levelValues = new ArrayList<>();
            for (int i = 0; i < breadth; i++) {
                Node current = nodesEncountered.poll();
                levelValues.add(current.val);

                if (current.children != null) {
                    for (Node child : current.children) {
                        nodesEncountered.offer(child);
                    }
                }
            }
            values.add(levelValues);
        }
        return values;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

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
