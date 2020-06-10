package edu.codifyme.leetcode.interview.googledeck.list;

/**
 * MEDIUM:
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list
 *
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Follow up:
 * Could you do this in one pass?
 *
 * Approach:
 * The above algorithm could be optimized to one pass. Instead of one pointer, we could use two pointers. The first
 * pointer advances the list by n+1n+1 steps from the beginning, while the second pointer starts from the beginning of
 * the list. Now, both pointers are exactly separated by nn nodes apart. We maintain this constant gap by advancing both
 * pointers together until the first pointer arrives past the last node. The second pointer will be pointing at the nnth
 * node counting from the last. We relink the next pointer of the node referenced by the second pointer to point to the
 * node's next next node.
 */
public class RemoveNthNodeFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
