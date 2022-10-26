public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public boolean equals(ListNode other) {
        if (this == null || other == null) return this == other;

        if (other.val != this.val) return false;

        if (this.next == null || other.next == null) return this.next == other.next;
        return this.next.equals(other.next);
    }
}
