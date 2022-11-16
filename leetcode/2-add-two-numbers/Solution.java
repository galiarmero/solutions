class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode tail = result;
        int carry = 0;

        while (l1 != null || l2 != null || carry > 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val; // add value to sum
                l1 = l1.next; // traverse the next node
            }
            if (l2 != null) {
                sum += l2.val; // add value to sum
                l2 = l2.next; // traverse the next node
            }

            ListNode newNode = new ListNode(sum % 10);
            carry = sum / 10;
            tail.next = newNode;
            tail = tail.next;
        }

        return result.next;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        ListNode a = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode b = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode ab = new ListNode(7, new ListNode(0, new ListNode(8)));
        assert ab.equals(s.addTwoNumbers(a, b));

        ListNode c = new ListNode(0);
        ListNode d = new ListNode(0);
        ListNode cd = new ListNode(0);
        assert cd.equals(s.addTwoNumbers(c, d));

        ListNode e = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))));
        ListNode f = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));
        ListNode ef = new ListNode(8, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(0, new ListNode(0, new ListNode(0, new ListNode(1))))))));
        assert ef.equals(s.addTwoNumbers(e, f));
    }
}
