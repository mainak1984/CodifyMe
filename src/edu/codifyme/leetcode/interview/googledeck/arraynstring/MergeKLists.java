package edu.codifyme.leetcode.interview.googledeck.arraynstring;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * HARD:
 *
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
 *
 * Algorithm
 *
 * Compare every \text{k}k nodes (head of every linked list) and get the node with the smallest value.
 * Extend the final sorted linked list with the selected nodes.
 * optimize the comparison process by priority queue
 *
 * Approach 5: Merge with Divide And Conquer
 * Intuition & Algorithm:
 * This approach walks alongside the one above but is improved a lot. We don't need to traverse most nodes many times repeatedly
 * Pair up \text{k}k lists and merge each pair.
 * After the first pairing, \text{k}k lists are merged into k/2k/2 lists with average 2N/k2N/k length, then k/4k/4, k/8k/8 and so on.
 * Repeat this procedure until we get the final sorted linked list.
 * Thus, we'll traverse almost NN nodes per pairing and merging, and repeat this procedure about \log_{2}{k}log
 * 2
 *  k times.
 */

public class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(-1);
        ListNode current = head;

        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode l1, ListNode l2) {
                return Integer.compare(l1.val, l2.val);
            }
        });

        // prefill heap
        for (ListNode listHead: lists) {
            if (null != listHead) {
                queue.add(listHead);
            }
        }

        while (queue.size() > 0) {
            // remove one, put its next until it is null
            ListNode topNode = queue.poll();

            current.next = topNode;
            current = topNode;

            // continue till q size is 0
            if ( null != topNode.next ) {
                queue.add(topNode.next);
            }
        }

        return head.next;
    }

//    public ListNode mergeKLists(ListNode[] lists) {
//        return mergeKLists(lists, 0, lists.length - 1);
//    }
//
//    public ListNode mergeKLists(ListNode[] lists, int start, int end) {
//
//        // base cases
//        if (start > end)
//            return null;
//        if (start == end)
//            return lists[start];
//
//        // divide and conquer
//        int middle = (end + start) / 2;
//        ListNode leftList = mergeKLists(lists, start, middle);
//        ListNode rightList = mergeKLists(lists, middle + 1, end);
//        return merge2Lists(leftList, rightList);
//    }
//
//
//    public ListNode merge2Lists(ListNode l1, ListNode l2) {
//        ListNode head = new ListNode(-1);
//
//        ListNode prev = head;
//        while(l1 != null && l2 != null) {
//            if (l1.val <= l2.val) {
//                prev.next = l1;
//                l1 = l1.next;
//            } else {
//                prev.next = l2;
//                l2 = l2.next;
//            }
//            prev = prev.next;
//        }
//        prev.next = (l1 == null ? l2:l1);
//
//        return head.next;
//    }

    // Definition for singly-linked list.
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
