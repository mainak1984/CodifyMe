package edu.codifyme.leetcode.practice.list;

/**
 * MEDIUM: Swap Nodes in Pairs
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 */
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if (null == head) {
            return head;
        }

        ListNode root = head.next;
        ListNode node = head;
        ListNode prev = null;

        while (node != null && node.next != null) {
            ListNode curr = node;
            ListNode next = node.next;

            curr.next = next.next;
            next.next = curr;

            if (prev != null) {
                prev.next = next;
            }

            prev = curr;
            node = curr.next;
        }

        return root == null ? head : root;
    }

    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
