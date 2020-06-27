package edu.codifyme.leetcode.interview.topinterviewquestions.tree;

/** EASY: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/ */

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of
 * every node never differ by more than 1.
 *
 * Example:
 *
 * Given the sorted array: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class SortedArrayToBST {
     class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
     }

    public TreeNode sortedArrayToBST(int[] nums) {
        return getNodeFromArray(nums, 0, nums.length-1);
    }

    public TreeNode getNodeFromArray(int[] nums, int l, int r) {
        if (r < l ) {
            return null;
        }

        int m = (l+r)/2;
        TreeNode node = new TreeNode(nums[m]);
        node.left = getNodeFromArray(nums, l, m - 1);
        node.right = getNodeFromArray(nums, m + 1, r);
        return node;
    }
}
