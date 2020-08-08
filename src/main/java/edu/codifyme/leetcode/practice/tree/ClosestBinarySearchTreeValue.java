package edu.codifyme.leetcode.practice.tree;

/**
 * EASY: Closest Binary Search Tree Value
 * https://leetcode.com/problems/closest-binary-search-tree-value
 *
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 *
 * Note:
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest to the target.
 * Example:
 *
 * Input: root = [4,2,5,1,3], target = 3.714286
 *
 *     4
 *    / \
 *   2   5
 *  / \
 * 1   3
 *
 * Output: 4
 *
 * Approach:
 * Do binary traversal and keep noting the min value and element
 */
public class ClosestBinarySearchTreeValue {
    int element;
    double minVal = Double.MAX_VALUE;

    public int closestValue(TreeNode root, double target) {
        closest(root, target);
        return element;
    }

    void closest(TreeNode node, double target) {
        if (node == null) {
            return;
        }

        if ( Math.abs((double)node.val-target) < minVal) {
            minVal = Math.abs((double)node.val-target);
            element = node.val;
        }

        if (minVal == 0) {
            return;
        }

        if ((double)node.val < target && node.right != null) {
            closest(node.right, target);
        } else if ((double)node.val > target && node.left != null) {
            closest(node.left, target);
        }
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
