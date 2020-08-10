package edu.codifyme.leetcode.interview.google.treesngrapsh;

/**
 * 543. Diameter of Binary Tree
 * EASY: https://leetcode.com/problems/diameter-of-binary-tree/
 *
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the
 * length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *
 * Example:
 * Given a binary tree
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Note: The length of path between two nodes is represented by the number of edges between them.
 */
public class DiameterOfBinaryTree {
    int maxSoFar = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        findDia(root);
        return maxSoFar - 1 < 0? 0: maxSoFar - 1;
    }

    public int findDia(TreeNode root) {
        if (null == root) {
            return 0;
        }

        int leftLen = findDia(root.left);
        int rightLen = findDia(root.right);

        maxSoFar = Math.max(maxSoFar, leftLen + rightLen + 1);

        return Math.max(leftLen + 1, rightLen + 1);
    }

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
