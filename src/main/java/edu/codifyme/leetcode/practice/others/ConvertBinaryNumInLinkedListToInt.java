package edu.codifyme.leetcode.practice.others;

/**
 * 1290. Convert Binary Number in a Linked List to Integer.
 * EASY: https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/
 *
 * Given head which is a reference node to a singly-linked list. The value of each node in the linked list is either
 * 0 or 1. The linked list holds the binary representation of a number.
 *
 * Return the decimal value of the number in the linked list.
 *
 * Example 1:
 * Input: head = [1,0,1]
 * Output: 5
 * Explanation: (101) in base 2 = (5) in base 10
 *
 * Example 2:
 * Input: head = [0]
 * Output: 0
 *
 * Example 3:
 * Input: head = [1]
 * Output: 1
 *
 * Example 4:
 * Input: head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
 * Output: 18880
 *
 * Example 5:
 * Input: head = [0,0]
 * Output: 0
 *
 * Constraints:
 * The Linked List is not empty.
 * Number of nodes will not exceed 30.
 * Each node's value is either 0 or 1.
 *
 * Approach: Bitwise manipulation
 * - Initialize result number to be equal to head value: num = head.val. This operation is safe because the list is
 * guaranteed to be non-empty.
 * - Parse linked list starting from the head: while head.next:
 *      - The current value is head.next.val. Update the result by shifting it by one to the left and adding the
 *      current value using logical OR: num = (num << 1) | head.next.val.
 * - Return num.
 */
public class ConvertBinaryNumInLinkedListToInt {
    public int getDecimalValue(ListNode head) {
        int sum = 0;

        while (head != null) {
            sum = (sum << 1) | head.val;
            head = head.next;
        }

        return sum;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
