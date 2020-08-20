package edu.codifyme.leetcode.practice.list;

/**
 * 143. Reorder List
 * https://leetcode.com/problems/reorder-list/
 *
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * Example 1:
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 *
 * Example 2:
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 *
 * Approach 1:
 * Find the mid point and reverse the remaining list. Merge both the pieces.
 *
 * Approach 2:
 * Use recursion to merge 1st, 2nd element keeping recur(3rd onwards) in the middle
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        ListNode first = head;
        ListNode second = null;
        int len = 0;

        while (first != null) {
            first = first.next;
            len++;
        }

        if (len == 0 || len == 1 || len == 2) {
            return;
        }

        first = head;
        for (int i = 0; i < (len-1)/2; i++) {
            first = first.next;
        }

        ListNode curr = first.next;
        first.next = null;

        while (curr != null) {
            ListNode node = curr;
            curr = curr.next;
            node.next = second;
            second = node;
        }

        // merge list
        first = head;
        while (second != null) {
            ListNode temp = second;

            second = second.next;

            temp.next = first.next;
            first.next = temp;

            first = temp.next;
        }
    }

//     public void reorderList(ListNode head) {
//         if(head == null || head.next == null || head.next.next == null) return;
//         // have two pointer slow and fast
//         ListNode slow = head, fast = head.next.next;
//         // once fast hit the end than take slow pointer + 1 and start adding in recursive backword manner
//         reorderListUsingSlowAndFastPointer(slow, fast, true);
//     }

//     public ListNode reorderListUsingSlowAndFastPointer(ListNode slow, ListNode fast, boolean isEvenSize) {
//             ListNode newNext = null;
//             if(fast != null) {
//                 newNext = reorderListUsingSlowAndFastPointer(slow.next, fast.next == null ? null : fast.next.next, fast.next == null ? false : true);
//             }
//             ListNode temp = isEvenSize && newNext == null ? slow.next.next : slow.next;
//             ListNode oldNewNext = newNext == null ? temp : newNext.next;
//             if(isEvenSize && newNext == null) slow.next.next = newNext; else slow.next = newNext;
//             if(newNext != null) newNext.next = temp;
//             return oldNewNext;
//     }

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
