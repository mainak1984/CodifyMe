package edu.codifyme.leetcode.interview.facebook.treesngraphs;

/**
 * 114. Flatten Binary Tree to Linked List
 * MEDIUM: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 *
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the
 * list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 * Example 1:
 *
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 *
 * Example 2:
 * Input: root = []
 * Output: []
 *
 * Example 3:
 * Input: root = [0]
 * Output: [0]
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 *
 * Approach:
 * Traverse right child and add its first flattened node behind traversed left child, finally attach it to node's left
 *
 * Algorithm
 * We'll have a separate function for flattening out the tree since the main function provided in the problem isn't
 * supposed to return anything and our algorithm will return the tail node of the flattened out tree.
 *
 * For a given node, we will recursively flatten out the left and the right subtrees and store their corresponding tail
 * nodes in leftTail and rightTail respectively.
 *
 * Next, we will make the following connections (only if there is a left child for the current node, else the leftTail
 * would be null)
 *
 *  leftTail.right = node.right
 *  node.right = node.left
 *  node.left = None
 *
 * Next we have to return the tail of the final, flattened out tree rooted at node. So, if the node has a right child,
 * then we will return the rightTail, else, we'll return the leftTail.
 */
public class FlattenBinaryTreetoLinkedList {
    public void flatten(TreeNode root) {
        if (root != null) {
            traverse(root, null);
        }
    }

    TreeNode traverse(TreeNode node, TreeNode next) {
        TreeNode right = null;

        if (node.right != null) {
            right = traverse(node.right, next);
        } else {
            right = next;
        }

        if (node.left != null) {
            node.right = traverse(node.left, right);
            node.left = null;
        } else {
            node.right = right;
        }

        return node;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
