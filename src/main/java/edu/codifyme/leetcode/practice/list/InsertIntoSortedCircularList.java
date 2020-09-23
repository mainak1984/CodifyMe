package edu.codifyme.leetcode.practice.list;

/**
 * 708. Insert into a Sorted Circular Linked List
 * MEDIUM: https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/
 *
 * Given a node from a Circular Linked List which is sorted in ascending order, write a function to insert a value
 * insertVal into the list such that it remains a sorted circular list. The given node can be a reference to any single
 * node in the list, and may not be necessarily the smallest value in the circular list.
 *
 * If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the
 * insertion, the circular list should remain sorted.
 *
 * If the list is empty (i.e., given node is null), you should create a new single circular list and return the
 * reference to that single node. Otherwise, you should return the original given node.
 *
 * Example 1:
 * Input: head = [3,4,1], insertVal = 2
 * Output: [3,4,1,2]
 * Explanation: In the figure above, there is a sorted circular list of three elements. You are given a reference to the
 * node with value 3, and we need to insert 2 into the list. The new node should be inserted between node 1 and node 3.
 * After the insertion, the list should look like this, and we should still return node 3.
 *
 * Example 2:
 * Input: head = [], insertVal = 1
 * Output: [1]
 * Explanation: The list is empty (given head is null). We create a new single circular list and return the reference to
 * that single node.
 *
 * Example 3:
 * Input: head = [1], insertVal = 0
 * Output: [1,0]
 *
 * Constraints:
 * 0 <= Number of Nodes <= 5 * 10^4
 * -10^6 <= Node.val <= 10^6
 * -10^6 <= insertVal <= 10^6
 *
 * Approach:
 * Algorithm
 * First of all, let us define the skeleton of two-pointers iteration algorithm as follows:
 * - As we mentioned in the intuition, we loop over the linked list with two pointers (i.e. prev and curr) step by
 *   step. The termination condition of the loop is that we get back to the starting point of the two pointers (i.e.
 *   prev == head)
 * - During the loop, at each step, we check if the current place bounded by the two pointers is the right place to
 * insert the new value.
 * - If not, we move both pointers one step forwards.
 */
public class InsertIntoSortedCircularList {
    public Node insert(Node head, int insertVal) {
        Node placeholder = new Node(insertVal);
        if (null == head) {
            placeholder.next = placeholder;
            head = placeholder;
        } else {
            Node prevNode = head;
            Node currNode = head.next;

            while (currNode != head) {
                if (prevNode.val == insertVal || (prevNode.val < insertVal && currNode.val >= insertVal) ||
                        (prevNode.val > currNode.val && (currNode.val >= insertVal || prevNode.val <= insertVal) )) {
                    placeholder.next = prevNode.next;
                    prevNode.next = placeholder;
                    break;
                }
                prevNode = currNode;
                currNode = currNode.next;
            }

            if (currNode == head) {
                placeholder.next = currNode;
                prevNode.next = placeholder;
            }
        }
        return head;
    }

//     public Node insert(Node head, int insertVal) {
//         if (head == null) {
//           Node newNode = new Node(insertVal, null);
//           newNode.next = newNode;
//           return newNode;
//         }

//         Node prev = head;
//         Node curr = head.next;
//         boolean toInsert = false;

//         do {
//           if (prev.val <= insertVal && insertVal <= curr.val) {
//             // Case 1).
//             toInsert = true;
//           } else if (prev.val > curr.val) {
//             // Case 2).
//             if (insertVal >= prev.val || insertVal <= curr.val)
//               toInsert = true;
//           }

//           if (toInsert) {
//             prev.next = new Node(insertVal, curr);
//             return head;
//           }

//           prev = curr;
//           curr = curr.next;
//         } while (prev != head);

//         // Case 3).
//         prev.next = new Node(insertVal, curr);
//         return head;
//       }

    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    };
}
