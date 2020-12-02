package edu.codifyme.leetcode.practice.others;

/**
 * 382. Linked List Random Node
 * https://leetcode.com/problems/linked-list-random-node/
 *
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same
 * probability of being chosen.
 *
 * Follow up:
 * What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without
 * using extra space?
 *
 * Example:
 * // Init a singly linked list [1,2,3].
 * ListNode head = new ListNode(1);
 * head.next = new ListNode(2);
 * head.next.next = new ListNode(3);
 * Solution solution = new Solution(head);
 *
 * // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 * solution.getRandom();
 *
 * Approach 1: Fixed-Range Sampling
 * Intuition
 * First of all, let us talk about the elephant in the room. Yes, the problem could be as simple as choosing a random
 * sample from a list, which in our case happens to be a linked list.
 *
 * If we are given an array or a linked list with a known size, then it would be a no brainer to solve the problem.
 *
 * Approach 2: Reservoir Sampling
 * Intuition
 * In order to do random sampling over a population of unknown size with constant space, the answer is reservoir
 * sampling. As one will see later, it is an elegant algorithm that can address the two caveats of the previous approach.
 *
 * The reservoir sampling algorithm is intended to sample k elements from an population of unknown size. In our case,
 * the k happens to be one.
 *
 * Furthermore, the reservoir sampling is a family of algorithms which includes several variants over the time. Here we
 * present a simple albeit slow one
 */
public class LinkedListRandomNode {

    // Approach: Reservoir sampling
    private ListNode head;

    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public LinkedListRandomNode(ListNode head) {
        this.head = head;
    }

    /** Returns a random node's value. */
    public int getRandom() {
        int scope = 1, chosenValue = 0;
        ListNode curr = this.head;
        while (curr != null) {
            // decide whether to include the element in reservoir
            if (Math.random() < 1.0 / scope)
                chosenValue = curr.val;
            // move on to the next node
            scope += 1;
            curr = curr.next;
        }
        return chosenValue;
    }

    // Approach: inefficient, but straight forward
//    int size = 0;
//    ListNode head = null;
//
//    /** @param head The linked list's head.
//    Note that the head is guaranteed to be not null, so it contains at least one node. */
//    public LinkedListRandomNode(ListNode head) {
//        this.head = head;
//
//        while (head != null) {
//            size++;
//            head = head.next;
//        }
//    }
//
//    /** Returns a random node's value. */
//    public int getRandom() {
//        int pick = (int)(Math.random() * size);
//        ListNode curr = head;
//
//        for (int i = 0; i < pick; i++) {
//            curr = curr.next;
//        }
//
//        return curr.val;
//    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
