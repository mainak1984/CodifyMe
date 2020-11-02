package edu.codifyme.leetcode.practice.list;

/**
 * 147. Insertion Sort List
 * MEDIUM: https://leetcode.com/problems/insertion-sort-list/
 *
 * Sort a linked list using insertion sort.
 *
 * A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in
 * the list.
 * With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list
 *
 * Algorithm of Insertion Sort:
 * Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
 * It repeats until no input elements remain.
 *
 * Example 1:
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 *
 * Example 2:
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 *
 * Approach:
 * Let us first review the idea of insertion sort algorithm, which can be broke down into the following steps:
 * - First of all, we create an empty list which would be used to hold the results of sorting.
 * -  We then iterate through each element in the input list. For each element, we need to find a proper position in
 *  the resulting list to insert the element, so that the order of the resulting list is maintained.
 *  - As one can see, once the iteration in the above step terminates, we will obtain the resulting list where the
 *  elements are ordered.
 *
 *  To translate the above intuition into the implementation, we applied two tricks.
 *
 * The first trick is that we will create a pseudo_head node which serves as a pointer pointing to the resulting list.
 * More precisely, this node facilitates us to always get a hold on the resulting list, especially when we need to
 * insert a new element to the head of the resulting list. One will see later in more details how it can greatly
 * simplify the logic.
 * In a singly-linked list, each node has only one pointer that points to the next node. If we would like to insert a
 * new node (say B) before certain node (say A), we need to know the node (say C) that is currently before the node A,
 * i.e. C -> A. With the reference in the node C, we could now insert the new node, i.e. C -> B -> A.
 * Given the above insight, in order to insert a new element into a singly-linked list, we apply another trick.
 * The idea is that we use a pair of pointers (namely prev_node -> next_node) which serve as place-holders to guard the
 * position where in-between we would insert a new element (i.e. prev_node -> new_node -> next_node).
 */
public class InsertionSortList {
    // Implementation 1: compact
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;

        while(head != null){
            ListNode temp = head.next;

            // Very important optimization
            if(prev.val >= head.val){
                prev = dummy;
            }

            while(prev.next != null && prev.next.val < head.val){
                prev = prev.next;
            }

            head.next = prev.next;
            prev.next = head;
            head = temp;
        }
        return dummy.next;
    }

    // Implementation 2:
//    public ListNode insertionSortList(ListNode head) {
//        if (head == null || head.next == null) {
//            return head;
//        }
//
//        ListNode newHead = new ListNode(Integer.MIN_VALUE, head);
//        ListNode preTarget = newHead;
//        ListNode target = newHead.next;
//        ListNode curr = newHead;
//
//        while (target != null) {
//            // Very important optimization
//            if (curr.val >= target.val) {
//                curr = newHead;
//            }
//
//            while (curr.next != target) {
//                if (target.val >= curr.val && target.val <= curr.next.val) {
//                    preTarget.next = target.next;
//                    target.next = curr.next;
//                    curr.next = target;
//                    break;
//                }
//                curr = curr.next;
//            }
//
//            if (preTarget.next == target) {
//                preTarget = preTarget.next;
//            }
//            target = preTarget.next;
//        }
//
//        return newHead.next;
//    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
