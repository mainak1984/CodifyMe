package edu.codifyme.leetcode.practice.tree;

/**
 * 510. Inorder Successor in BST II
 * MEDIUM: https://leetcode.com/problems/inorder-successor-in-bst-ii/
 *
 * Given a node in a binary search tree, find the in-order successor of that node in the BST.
 * If that node has no in-order successor, return null.
 * The successor of a node is the node with the smallest key greater than node.val.
 *
 * You will have direct access to the node but not to the root of the tree. Each node will have a reference to its
 * parent node. Below is the definition for Node:
 *
 * Follow up:
 * Could you solve it without looking up any of the node's values?
 *
 * Example 1:
 * Input: tree = [2,1,3], node = 1
 * Output: 2
 * Explanation: 1's in-order successor node is 2. Note that both the node and the return value is of Node type.
 *
 * Example 2:
 * Input: tree = [5,3,6,2,4,null,null,1], node = 6
 * Output: null
 * Explanation: There is no in-order successor of the current node, so the answer is null.
 *
 * Example 3:
 * Input: tree = [15,6,18,3,7,17,20,2,4,null,13,null,null,null,null,null,null,null,null,9], node = 15
 * Output: 17
 *
 * Example 4:
 * Input: tree = [15,6,18,3,7,17,20,2,4,null,13,null,null,null,null,null,null,null,null,9], node = 13
 * Output: 15
 *
 * Example 5:
 * Input: tree = [0], node = 0
 * Output: null
 *
 * Constraints:
 * -10^5 <= Node.val <= 10^5
 * 1 <= Number of Nodes <= 10^4
 * All Nodes will have unique values.
 *
 * Approach:
 * There are two possible situations here :
 * - Node has a right child, and hence its successor is somewhere lower in the tree. To find the successor, go to the
 *   right once and then as many times to the left as you could
 * - Node has no right child, then its successor is somewhere upper in the tree. To find the successor, go up till the
 *   node that is left child of its parent. The answer is the parent. Beware that there could be no successor (= null
 *   successor) in such a situation.
 */
public class InorderSuccessorInBSTII {
    public Node inorderSuccessor(Node node) {
        if (node == null || (node.right == null && node.parent == null) ) {
            return null;
        }
        int val = node.val;

        if (node.right == null && node.parent.val > val) {
            return node.parent;
        }

        if (node.right != null) {
            return findSuccessor(node);
        } else {
            while (node != null && node.val <= val) {
                node = node.parent;
            }
            return node;
        }
    }

    Node findSuccessor(Node current) {
        Node node = null;
        current = current.right;

        while (current != null) {
            node = current;
            current = current.left;
        }

        return node;
    }

    // Tutorial implementation
//     public Node inorderSuccessor(Node x) {
//         // the successor is somewhere lower in the right subtree
//         if (x.right != null) {
//           x = x.right;
//           while (x.left != null) x = x.left;
//           return x;
//         }

//         // the successor is somewhere upper in the tree
//         while (x.parent != null && x == x.parent.right) x = x.parent;
//         return x.parent;
//       }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
}
