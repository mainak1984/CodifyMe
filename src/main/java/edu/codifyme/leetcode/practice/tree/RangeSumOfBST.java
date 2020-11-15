package edu.codifyme.leetcode.practice.tree;

/**
 * 938. Range Sum of BST
 * EASY: https://leetcode.com/problems/range-sum-of-bst/
 *
 * Given the root node of a binary search tree, return the sum of values of all nodes with a value in the range [low, high].
 *
 * Example 1:
 * Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
 * Output: 32
 *
 * Example 2:
 * Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 * Output: 23
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 2 * 104].
 * 1 <= Node.val <= 105
 * 1 <= low <= high <= 105
 * All Node.val are unique.
 *
 * Approach:
 * We traverse the tree using a depth first search. If node.val falls outside the range [L, R],
 * (for example node.val < L), then we know that only the right branch could have nodes with value inside [L, R].
 */
public class RangeSumOfBST {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }

        int sum = 0;
        if (root.val >= low && root.val <= high) {
            sum = root.val;
        }

        if (root.val >= low) {
            sum += rangeSumBST(root.left, low, high);
        }

        if (root.val <= high) {
            sum += rangeSumBST(root.right, low, high);
        }

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
