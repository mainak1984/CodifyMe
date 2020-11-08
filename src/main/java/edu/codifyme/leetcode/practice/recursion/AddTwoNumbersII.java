package edu.codifyme.leetcode.practice.recursion;

import java.util.Stack;

/**
 * 445. Add Two Numbers II
 * MEDIUM: https://leetcode.com/problems/add-two-numbers-ii/
 *
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes
 * first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 *
 * Example:
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 *
 * Approach 1:
 * reverse both list and add
 *
 * Approach 2:
 * Use stack and push the list; add items from list
 */
public class AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        Stack<Integer> st1 = new Stack<>();
        Stack<Integer> st2 = new Stack<>();

        while (l1 != null) {
            st1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            st2.push(l2.val);
            l2 = l2.next;
        }

        ListNode result = null;
        int carry = 0;

        while (!st1.isEmpty() || !st2.isEmpty()) {
            int val = (st1.isEmpty()? 0: st1.pop()) + (st2.isEmpty()? 0: st2.pop()) + carry;
            carry = val/10;
            val = val % 10;
            result = new ListNode(val, result);
        }

        if (carry != 0) {
            result = new ListNode(carry, result);
        }

        return result;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
