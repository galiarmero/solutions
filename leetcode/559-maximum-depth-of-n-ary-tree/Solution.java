import java.util.Arrays;

class Solution {
    public int maxDepth(Node root) {
        if (root == null) return 0;
        if (root.children == null) return 1;

        int maxChildDepth = 0;
        for (Node child : root.children) {
            int childDepth = maxDepth(child);
            if (childDepth > maxChildDepth) {
                maxChildDepth = childDepth;
            }
        }

        return 1 + maxChildDepth;
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
        assert s.maxDepth(a) == 3;

        // 2
        Node b = new Node(1, Arrays.asList(
            new Node(2),
            new Node(3, Arrays.asList(
                new Node(6),
                new Node(7, Arrays.asList(
                    new Node(11, Arrays.asList(
                        new Node(14)
                    ))
                ))
            )),
            new Node(4, Arrays.asList(
                new Node(8, Arrays.asList(
                    new Node(12)
                ))
            )),
            new Node(5, Arrays.asList(
                new Node(9, Arrays.asList(
                    new Node(13)
                )),
                new Node(10)
            ))
        ));
        assert s.maxDepth(b) == 5;

        // 3
        Node c = new Node(1);
        assert s.maxDepth(c) == 1;

        // 4
        assert s.maxDepth(null) == 0;
    }
}
