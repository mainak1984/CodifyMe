package edu.codifyme.leetcode.practice.tree;

/**
 * 116. Populating Next Right Pointers in Each Node
 * MEDIUM: https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 *
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The
 * binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should
 * be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 * Follow up:
 * You may only use constant extra space.
 * Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 *
 * Example 1:
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point
 * to its next right node, just like in Figure B. The serialized output is in level order as connected by the next
 * pointers, with '#' signifying the end of each level.
 *
 * Constraints:
 * The number of nodes in the given tree is less than 4096.
 * -1000 <= node.val <= 1000
 *
 * Approach 1:
 * Use a queue to perform level order traversal. This is unoptimized as we are using O9n) storage.
 *
 * Approach 2:
 * Use recursion to set pointer links. Use the next pointer for setting up right child's next pointer.
 * node.left.next = node.right, node.right.next = node.next.left
 * Traverse right child first followed by left child
 *
 * Approach 3: (optimum)
 * Perform a level order traversal using next pointer. Set next pointer for next level, use it while traversing next
 * level to perform level order traversal
 */
public class PopulatingNextRightPointersInEachNode {
    // Approach 3: Most optimized: Avoids recursion also
    public Node connect(Node root) {

        if (root == null) {
            return root;
        }

        // Start with the root node. There are no next pointers
        // that need to be set up on the first level
        Node leftmost = root;

        // Once we reach the final level, we are done
        while (leftmost.left != null) {

            // Iterate the "linked list" starting from the head
            // node and using the next pointers, establish the
            // corresponding links for the next level
            Node head = leftmost;

            while (head != null) {

                // CONNECTION 1
                head.left.next = head.right;

                // CONNECTION 2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }

                // Progress along the list (nodes on the current level)
                head = head.next;
            }

            // Move onto the next level
            leftmost = leftmost.left;
        }

        return root;
    }

    // Opimized: O(1) extra memory
//     public Node connect(Node root) {
//         if (root == null) {
//             return null;
//         }

//         if (root.left != null) {
//             root.left.next = root.right;
//         }

//         if (root.next != null && root.right != null) {
//             root.right.next = root.next.left;
//         }

//         connect(root.right);
//         connect(root.left);

//         return root;
//     }

    // Using Queue: Extra memory
//     public Node connect(Node root) {
//         if (root == null) {
//             return root;
//         }

//         // Initialize a queue data structure which contains
//         // just the root of the tree
//         Queue<Node> Q = new LinkedList<Node>();
//         Q.add(root);

//         // Outer while loop which iterates over
//         // each level
//         while (Q.size() > 0) {

//             // Note the size of the queue
//             int size = Q.size();

//             // Iterate over all the nodes on the current level
//             for(int i = 0; i < size; i++) {

//                 // Pop a node from the front of the queue
//                 Node node = Q.poll();

//                 // This check is important. We don't want to
//                 // establish any wrong connections. The queue will
//                 // contain nodes from 2 levels at most at any
//                 // point in time. This check ensures we only
//                 // don't establish next pointers beyond the end
//                 // of a level
//                 if (i < size - 1) {
//                     node.next = Q.peek();
//                 }

//                 // Add the children, if any, to the back of
//                 // the queue
//                 if (node.left != null) {
//                     Q.add(node.left);
//                 }
//                 if (node.right != null) {
//                     Q.add(node.right);
//                 }
//             }
//         }

//         // Since the tree has now been modified, return the root node
//         return root;
//     }


//     public Node connect(Node root) {
//         Queue<Node> queue = new LinkedList<>();

//         if (root == null) {
//             return root;
//         }

//         queue.add(root);

//         while (!queue.isEmpty()) {
//             Queue<Node> temp = new LinkedList<>();
//             Node next = null;

//             while (!queue.isEmpty()) {
//                 Node node = queue.poll();
//                 node.next = next;
//                 next = node;

//                 if (node.right != null) {
//                     temp.add(node.right);
//                 }
//                 if (node.left != null) {
//                     temp.add(node.left);
//                 }
//             }

//             queue = temp;
//         }

//         return root;
//     }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}
