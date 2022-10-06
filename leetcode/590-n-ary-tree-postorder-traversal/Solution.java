import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class Solution {
    public List<Integer> postorder(Node root) {
        LinkedList<Integer> values = new LinkedList<>();
        if (root == null) return values;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            values.addFirst(current.val);

            if (current.children != null) {
                for (Node child : current.children) {
                    if (child != null) {
                        stack.push(child);
                    }
                }
            }
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

        assert s.postorder(a).equals(Arrays.asList(5, 6, 3, 2, 4, 1));
    }
}
