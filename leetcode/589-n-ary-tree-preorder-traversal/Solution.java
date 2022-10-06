import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> values = new ArrayList<>();
        if (root == null) return values;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            values.add(current.val);

            if (current.children != null) {
                for (int i = current.children.size() - 1; i >= 0; i--) {
                    Node child = current.children.get(i);
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

        assert s.preorder(a).equals(Arrays.asList(1, 3, 5, 6, 2, 4));
    }
}
