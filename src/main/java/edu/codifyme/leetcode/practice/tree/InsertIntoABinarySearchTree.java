package edu.codifyme.leetcode.practice.tree;

/**
 * 701. Insert into a Binary Search Tree
 * MEDIUM: https://leetcode.com/problems/insert-into-a-binary-search-tree/
 *
 * You are given the root node of a binary search tree (BST) and a value to insert into the tree. Return the root node
 * of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
 *
 * Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion.
 * You can return any of them.
 *
 * Example 1:
 * Input: root = [4,2,7,1,3], val = 5
 * Output: [4,2,7,1,3,5]
 * Explanation: Another accepted tree is:
 *
 * Example 2:
 * Input: root = [40,20,60,10,30,50,70], val = 25
 * Output: [40,20,60,10,30,50,70,null,null,25]
 *
 * Example 3:
 * Input: root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
 * Output: [4,2,7,1,3,5]
 *
 * Constraints:
 * The number of nodes in the tree will be in the range [0, 104].
 * -108 <= Node.val <= 108
 * All the values Node.val are unique.
 * -108 <= val <= 108
 * It's guaranteed that val does not exist in the original BST.
 *
 * Approach:
 * Approach 1: Recursion
 * The recursion implementation is very straightforward :
 *  - If root is null - return TreeNode(val).
 *  - If val > root.val - go to insert into the right subtree.
 *  - If val < root.val - go to insert into the left subtree.
 *  - Return root
 */
public class InsertIntoABinarySearchTree {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val <= root.val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                insertIntoBST(root.left, val);
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                insertIntoBST(root.right, val);
            }
        }

        return root;
    }

    // public TreeNode insertIntoBST(TreeNode root, int val) {
    //     if (root == null) {
    //         return new TreeNode(val);
    //     }
    //     if (root.val > val) {
    //         root.left = insertIntoBST(root.left, val);
    //     } else if (root.val < val) {
    //         root.right = insertIntoBST(root.right, val);
    //     }
    //     return root;
    // }


    public class TreeNode {
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
