package edu.codifyme.leetcode.practice.searchnsort;

/**
 * 148. Sort List
 * MEDIUM: https://leetcode.com/problems/sort-list/
 *
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 *
 * Example 1:
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 *
 * Example 2:
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 *
 * Example 3:
 * Input: head = []
 * Output: []
 *
 * Constraints:
 * The number of nodes in the list is in the range [0, 5 * 104].
 * -105 <= Node.val <= 105
 *
 * Approach:
 * Algorithm: Top down merge sort
 * Recursively split the original list into two halves. The split continues until there is only one node in the linked
 * list (Divide phase). To split the list into two halves, we find the middle of the linked list using the Fast and Slow
 * pointer approach as mentioned in Find Middle Of Linked List.
 * Recursively sort each sublist and combine it into a single sorted list. (Merge Phase). This is similar to the problem
 * Merge two sorted linked lists
 * The process continues until we get the original list in sorted order.
 */
public class SortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode mid = getMid(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return merge(left, right);
    }

    ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
                tail = tail.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
                tail = tail.next;
            }
        }
        tail.next = (list1 != null) ? list1 : list2;
        return dummyHead.next;
    }

    ListNode getMid(ListNode head) {
        ListNode midPrev = null;
        while (head != null && head.next != null) {
            midPrev = (midPrev == null) ? head : midPrev.next;
            head = head.next.next;
        }
        ListNode mid = midPrev.next;
        midPrev.next = null;
        return mid;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
