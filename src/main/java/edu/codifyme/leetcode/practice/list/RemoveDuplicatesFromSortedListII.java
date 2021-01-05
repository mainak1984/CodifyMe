package edu.codifyme.leetcode.practice.list;

/**
 * 82. Remove Duplicates from Sorted List II
 * MEDIUM: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 *
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers
 * from the original list. Return the linked list sorted as well.
 *
 * Example 1:
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 *
 * Example 2:
 * Input: head = [1,1,1,2,3]
 * Output: [2,3]
 *
 * Constraints:
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 *
 * Approach:
 * Approach 1: Sentinel Head + Predecessor
 * Sentinel Head
 * - Let's start from the most challenging situation: the list head is to be removed.
 * - The standard way to handle this use case is to use the so-called Sentinel Node. Sentinel nodes are widely used for
 * trees and linked lists as pseudo-heads, pseudo-tails, etc. They are purely functional and usually don't hold any data.
 * Their primary purpose is to standardize the situation to avoid edge case handling.
 * - For example, let's use here pseudo-head with zero value to ensure that the situation "delete the list head" could
 * never happen, and all nodes to delete are "inside" the list.
 *
 * Delete Internal Nodes
 * - The input list is sorted, and we can determine if a node is a duplicate by comparing its value to the node after
 * it in the list. Step by step, we could identify the current sublist of duplicates.
 * - Now it's time to delete it using pointer manipulations. Note that the first node in the duplicates sublist should
 * be removed as well. That means that we have to track the predecessor of duplicates sublist, i.e., the last node
 * before the sublist of duplicates.
 * - Having predecessor, we skip the entire duplicate sublist and make predecessor to point to the node after the
 * sublist.
 */
public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        // sentinel
        ListNode sentinel = new ListNode(0, head);

        // predecessor = the last node
        // before the sublist of duplicates
        ListNode pred = sentinel;

        while (head != null) {
            // if it's a beginning of duplicates sublist
            // skip all duplicates
            if (head.next != null && head.val == head.next.val) {
                // move till the end of duplicates sublist
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // skip all duplicates
                pred.next = head.next;
                // otherwise, move predecessor
            } else {
                pred = pred.next;
            }

            // move forward
            head = head.next;
        }
        return sentinel.next;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
