package edu.codifyme.leetcode.practice.tree;

/**
 * 117. Populating Next Right Pointers in Each Node II
 * MEDIUM: https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
 *
 * Given a binary tree
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
 * Input: root = [1,2,3,4,5,null,7]
 * Output: [1,#,2,3,#,4,5,7,#]
 * Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its
 * next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers,
 * with '#' signifying the end of each level.
 *
 * Constraints:
 * The number of nodes in the given tree is less than 6000.
 * -100 <= node.val <= 100
 *
 * Approach 1: Level order traversal without using queue
 * if a node's left child is not null and right child is not null, set node.left.next = node.right
 * else if a node's left is not null or right is not null, set its right child as findNextNonNullChild(node.next)
 *
 * Approach 2: Using previously established next pointers
 * We have to process all the nodes of the tree. So we can't reduce the time complexity any further. However, we can try
 * and reduce the space complexity. The reason we need a queue here is because we don't have any idea about the structure
 * of the tree and the kind of branches it has and we need to access all the nodes on a common level, together, and
 * establish connections between them.
 *
 * Once we are done establishing the next pointers between the nodes, don't they kind of represent a linked list? After
 * the next connections are established, all the nodes on a particular level actually form a linked list via these next
 * pointers. Based on this idea, we have the following intuition for our space efficient algorithm:
 *
 * We only move on to the level N+1 when we are done establishing the next pointers for the level N. So, since we have
 * access to all the nodes on a particular level via the next pointers, we can use these next pointers to establish the
 * connections for the next level or the level containing their children.
 */
public class PopulatingNextRightPointersInEachNodeII {
    // Approach 1
    public Node connect1(Node root) {
        Node leftmost = root;

        while (leftmost != null) {
            Node head = leftmost;
            leftmost = null;

            while (head != null) {
                if (head.left != null) {
                    if (head.right != null) {
                        head.left.next = head.right;
                    } else {
                        head.left.next = findNextRight(head.next);
                    }

                    leftmost = (leftmost == null)? head.left: leftmost;
                }

                if (head.right != null) {
                    head.right.next = findNextRight(head.next);

                    leftmost = (leftmost == null)? head.right: leftmost;
                }

                head = head.next;
            }
        }

        return root;
    }

    Node findNextRight(Node node) {

        while (node != null) {
            if (node.left != null) {
                return node.left;
            }
            if (node.right != null) {
                return node.right;
            }
            node = node.next;
        }

        return node;
    }

    //Approach 2
    public Node connect2(Node root) {

        if (root == null) {
            return root;
        }

        // The root node is the only node on the first level and hence its the leftmost node for that level
        this.leftmost = root;

        // Variable to keep track of leading node on the "current" level
        Node curr = leftmost;

        // We have no idea about the structure of the tree, so, we keep going until we do find the last level.
        // the nodes on the last level won't have any children
        while (this.leftmost != null) {

            // "prev" tracks the latest node on the "next" level while "curr" tracks the latest node on the current level.
            this.prev = null;
            curr = this.leftmost;

            // We reset this so that we can re-assign it to the leftmost node of the next level. Also, if there isn't
            // one, this would help break us out of the outermost loop.
            this.leftmost = null;

            // Iterate on the nodes in the current level using the next pointers already established.
            while (curr != null) {

                // Process both the children and update the prev and leftmost pointers as necessary.
                this.processChild(curr.left);
                this.processChild(curr.right);

                // Move onto the next node.
                curr = curr.next;
            }
        }

        return root ;
    }

    Node prev, leftmost;

    public void processChild(Node childNode) {

        if (childNode != null) {

            // If the "prev" pointer is alread set i.e. if we already found atleast one node on the next level,
            // setup its next pointer
            if (this.prev != null) {
                this.prev.next = childNode;

            } else {

                // Else it means this child node is the first node we have encountered on the next level, so, we
                // set the leftmost pointer
                this.leftmost = childNode;
            }

            this.prev = childNode;
        }
    }

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
