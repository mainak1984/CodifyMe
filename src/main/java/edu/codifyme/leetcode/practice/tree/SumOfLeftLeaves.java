package edu.codifyme.leetcode.practice.tree;

/**
 * 404. Sum of Left Leaves
 * EASY: https://leetcode.com/problems/sum-of-left-leaves/
 *
 * Find the sum of all left leaves in a given binary tree.
 *
 * Example:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 *
 * Approach:
 * DFS and pass on isLeftChild flag from root. check for leaf node, if leftChild, add to sum
 */
public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeaves(root, false);
    }

    int sumOfLeaves(TreeNode node, boolean isLeftChild) {
        if (node == null) {
            return 0;
        }

        if (isLeftChild && node.left == null && node.right == null) {
            return node.val;
        }

        int sum = 0;
        sum += sumOfLeaves(node.left, true);
        sum += sumOfLeaves(node.right, false);

        return sum;
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
