package edu.codifyme.leetcode.interview.topinterviewquestions.list;

/**
 * Merge k Sorted Lists
 * HARD: https://leetcode.com/problems/merge-k-sorted-lists/
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */

public class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeKLists(lists, 0, lists.length - 1);
    }

    public ListNode mergeKLists(ListNode[] lists, int start, int end) {

        // base cases
        if (start > end)
            return null;
        if (start == end)
            return lists[start];

        // divide and conquer
        int middle = (end + start) / 2;
        ListNode leftList = mergeKLists(lists, start, middle);
        ListNode rightList = mergeKLists(lists, middle + 1, end);
        return merge2Lists(leftList, rightList);
    }


    public ListNode merge2Lists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);

        ListNode prev = head;
        while(l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = (l1 == null ? l2:l1);

        return head.next;
    }

    // Definition for singly-linked list.
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
