package edu.codifyme.leetcode.practice.list;

/**
 * 142. Linked List Cycle II
 * https://leetcode.com/problems/linked-list-cycle-ii/
 *
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
 * following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is
 * connected to. Note that pos is not passed as a parameter.
 *
 * Notice that you should not modify the linked list.
 *
 * Follow up:
 * Can you solve it using O(1) (i.e. constant) memory?
 *
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 *
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: tail connects to node index 0
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 *
 * Example 3:
 * Input: head = [1], pos = -1
 * Output: no cycle
 * Explanation: There is no cycle in the linked list.
 *
 * Constraints:
 * The number of the nodes in the list is in the range [0, 104].
 * -105 <= Node.val <= 105
 * pos is -1 or a valid index in the linked-list.
 *
 * Approach:
 *
 * Algorithm
 * Floyd's algorithm is separated into two distinct phases. In the first phase, it determines whether a cycle is present
 * in the list. If no cycle is present, it returns null immediately, as it is impossible to find the entrance to a
 * non-existant cycle. Otherwise, it uses the located "intersection node" to find the entrance to the cycle.
 *
 * Phase 1:
 * Here, we initialize two pointers - the fast hare and the slow tortoise. Then, until hare can no longer advance, we
 * increment tortoise once and hare twice.[1] If, after advancing them, hare and tortoise point to the same node, we
 * return it. Otherwise, we continue. If the while loop terminates without returning a node, then the list is acyclic,
 * and we return null to indicate as much.
 *
 * Here, the nodes in the cycle have been labelled from 0 to C−1, where C is the length of the cycle. The noncyclic
 * nodes have been labelled from −F to -1, where F is the number of nodes outside of the cycle. After F iterations,
 * tortoise points to node 0 and hare points to some node h, where F≡h(modC). This is because hare traverses 2F nodes
 * over the course of F iterations, exactly F of which are in the cycle. After C−h more iterations, tortoise obviously
 * points to node C−h, but (less obviously) hare also points to the same node. To see why, remember that hare traverses
 * 2(C−h) from its starting position of h:
 * h + 2(C-h) =2C−h
 *            ≡C−h(modC)
 * Therefore, given that the list is cyclic, hare and tortoise will eventually both point to the same node, known
 * henceforce as the intersection.
 *
 * Phase 2:
 * Given that phase 1 finds an intersection, phase 2 proceeds to find the node that is the entrance to the cycle. To do
 * so, we initialize two more pointers: ptr1, which points to the head of the list, and ptr2, which points to the
 * intersection. Then, we advance each of them by 1 until they meet; the node where they meet is the entrance to the
 * cycle, so we return it.
 *
 * We can harness the fact that hare moves twice as quickly as tortoise to assert that when hare and tortoise meet at
 * node h, hare has traversed twice as many nodes. Using this fact, we deduce the following:
 *
 * To compute the intersection point, let's note that the hare has traversed twice as many nodes as the tortoise, i.e.
 * 2d(tortoise)=d(hare), that means
 * 2(F + a) = F + nC + a, where n is some integer.
 * Hence the coordinate of the intersection point is F + a = nC.
 */
public class LinkedListCycleII {
    private ListNode getIntersect(ListNode head) {
        ListNode tortoise = head;
        ListNode hare = head;

        // A fast pointer will either loop around a cycle and meet the slow
        // pointer or reach the `null` at the end of a non-cyclic list.
        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
            if (tortoise == hare) {
                return tortoise;
            }
        }

        return null;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        // If there is a cycle, the fast/slow pointers will intersect at some
        // node. Otherwise, there is no cycle, so we cannot find an entrance to
        // a cycle.
        ListNode intersect = getIntersect(head);
        if (intersect == null) {
            return null;
        }

        // To find the entrance to the cycle, we have two pointers traverse at
        // the same speed -- one from the front of the list, and the other from
        // the point of intersection.
        ListNode ptr1 = head;
        ListNode ptr2 = intersect;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return ptr1;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
