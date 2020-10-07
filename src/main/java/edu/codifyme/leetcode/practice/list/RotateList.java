package edu.codifyme.leetcode.practice.list;

/**
 * 61. Rotate List
 * MEDIUM: https://leetcode.com/problems/rotate-list/
 *
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 *
 * Example 1:
 *
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 * Example 2:
 *
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 *
 * Approach: 1
 * - find the length
 * - use two pointer approach to identify the break node and last node
 * - stitch the last node and make the break at the break node
 *
 * Approach 2:
 * The nodes in the list are already linked, and hence the rotation basically means
 * - To close the linked list into the ring.
 * - To break the ring after the new tail and just in front of the new head.
 */
public class RotateList {
    // Approach 1:
    public ListNode rotateRight(ListNode head, int k) {
        int len = 0;
        ListNode curr = head;

        // get list length
        while ( curr != null) {
            curr = curr.next;
            len++;
        }

        if (len == 0 || k % len == 0) {
            return head;
        }

        ListNode first = head;
        ListNode second = head;
        // two pointer to find the last elem of both pieces
        for (int i = 0; i < k % len; i++) {
            second = second.next;
        }

        while (second.next != null) {
            second = second.next;
            first = first.next;
        }

        // stich it in front
        second.next = head;
        head = first.next;
        first.next = null;

        return head;
    }

    // Approach 2:
//    public ListNode rotateRight(ListNode head, int k) {
//        // base cases
//        if (head == null) return null;
//        if (head.next == null) return head;
//
//        // close the linked list into the ring
//        ListNode old_tail = head;
//        int n;
//        for(n = 1; old_tail.next != null; n++)
//            old_tail = old_tail.next;
//        old_tail.next = head;
//
//        // find new tail : (n - k % n - 1)th node
//        // and new head : (n - k % n)th node
//        ListNode new_tail = head;
//        for (int i = 0; i < n - k % n - 1; i++)
//            new_tail = new_tail.next;
//        ListNode new_head = new_tail.next;
//
//        // break the ring
//        new_tail.next = null;
//
//        return new_head;
//    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
