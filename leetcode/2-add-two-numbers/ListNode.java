public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public String toString() {
        return this == null ? "-" : String.valueOf(this.val);
    }

    public boolean equals(ListNode other) {
        System.out.printf("this: %s, other: %s\n", this, other);
        if (this == null || other == null) return this == other;

        if (other.val != this.val) return false;

        if (this.next == null) return other.next == null;
        return this.next.equals(other.next);
    }
}
