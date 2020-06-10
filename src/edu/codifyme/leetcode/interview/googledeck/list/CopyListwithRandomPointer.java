package edu.codifyme.leetcode.interview.googledeck.list;

import java.util.HashMap;
import java.util.Map;

/**
 * MEDIUM:
 * https://leetcode.com/problems/copy-list-with-random-pointer
 *
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the
 * list or null.
 * Return a deep copy of the list.
 * The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val,
 * random_index] where:
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not
 * point to any node.
 *
 * Example 1:
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * Example 2:
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 *
 * Example 3:
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 *
 * Example 4:
 * Input: head = []
 * Output: []
 * Explanation: Given linked list is empty (null pointer), so return null.
 *
 * Constraints:
 *
 * -10000 <= Node.val <= 10000
 * Node.random is null or pointing to a node in the linked list.
 * Number of Nodes will not exceed 1000.
 *
 * Approach 1: Recursive
 * The basic idea behind the recursive solution is to consider the linked list like a graph. Every node of the Linked
 * List has 2 pointers (edges in a graph). Since, random pointers add the randomness to the structure we might visit the
 * same node again leading to cycles.
 * In the diagram above we can see the random pointer points back to the previously seen node hence leading to a cycle.
 * We need to take care of these cycles in the implementation.
 * All we do in this approach is to just traverse the graph and clone it. Cloning essentially means creating a new node
 * for every unseen node you encounter. The traversal part will happen recursively in a depth first manner. Note that we
 * have to keep track of nodes already processed because, as pointed out earlier, we can have cycles because of the random
 * pointers.
 *
 * Approach 3: Iterative with O(1)O(1) Space
 * Instead of a separate dictionary to keep the old node --> new node mapping, we can tweak the original linked list and
 * keep every cloned node next to its original node. This interleaving of old and new nodes allows us to solve this
 * problem without any extra space. Lets look at how the algorithm works.
 */
public class CopyListwithRandomPointer {
    public Node copyRandomList(Node head) {
        Node current = new Node(0);
        Node newHead = current;
        Node oldHead = head;

        Map<Node, Node> map = new HashMap<>();

        // First create all nodes
        while(head != null) {
            current.next = new Node(head.val);
            map.put(head, current.next);
            current = current.next;
            head = head.next;
        }

        current = newHead;
        head = oldHead;
        // pass through and connect randon pointers
        while (head != null) {
            if (head.random != null) {
                Node random  = map.get(head.random);
                current.next.random = random;
            }
            current = current.next;
            head = head.next;
        }

        return newHead.next;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

// APPROACH 1
//    HashMap<Node, Node> visitedHash = new HashMap<Node, Node>();
//
//    public Node copyRandomList(Node head) {
//
//        if (head == null) {
//            return null;
//        }
//
//        // If we have already processed the current node, then we simply return the cloned version of
//        // it.
//        if (this.visitedHash.containsKey(head)) {
//            return this.visitedHash.get(head);
//        }
//
//        // Create a new node with the value same as old node. (i.e. copy the node)
//        Node node = new Node(head.val, null, null);
//
//        // Save this value in the hash map. This is needed since there might be
//        // loops during traversal due to randomness of random pointers and this would help us avoid
//        // them.
//        this.visitedHash.put(head, node);
//
//        // Recursively copy the remaining linked list starting once from the next pointer and then from
//        // the random pointer.
//        // Thus we have two independent recursive calls.
//        // Finally we update the next and random pointers for the new node created.
//        node.next = this.copyRandomList(head.next);
//        node.random = this.copyRandomList(head.random);
//
//        return node;
//    }

// APPROACH 3
//    public Node copyRandomList(Node head) {
//
//        if (head == null) {
//            return null;
//        }
//
//        // Creating a new weaved list of original and copied nodes.
//        Node ptr = head;
//        while (ptr != null) {
//
//            // Cloned node
//            Node newNode = new Node(ptr.val);
//
//            // Inserting the cloned node just next to the original node.
//            // If A->B->C is the original linked list,
//            // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
//            newNode.next = ptr.next;
//            ptr.next = newNode;
//            ptr = newNode.next;
//        }
//
//        ptr = head;
//
//        // Now link the random pointers of the new nodes created.
//        // Iterate the newly created list and use the original nodes' random pointers,
//        // to assign references to random pointers for cloned nodes.
//        while (ptr != null) {
//            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
//            ptr = ptr.next.next;
//        }
//
//        // Unweave the linked list to get back the original linked list and the cloned list.
//        // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
//        Node ptr_old_list = head; // A->B->C
//        Node ptr_new_list = head.next; // A'->B'->C'
//        Node head_old = head.next;
//        while (ptr_old_list != null) {
//            ptr_old_list.next = ptr_old_list.next.next;
//            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
//            ptr_old_list = ptr_old_list.next;
//            ptr_new_list = ptr_new_list.next;
//        }
//        return head_old;
//    }
}
