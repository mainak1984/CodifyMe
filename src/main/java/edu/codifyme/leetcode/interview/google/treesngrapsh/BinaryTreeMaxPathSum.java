package edu.codifyme.leetcode.interview.google.treesngrapsh;

/**
 * HARD:
 * https://leetcode.com/problems/binary-tree-maximum-path-sum
 *
 * Given a non-empty binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along
 * the parent-child connections. The path must contain at least one node and does not need to go through the root.
 *
 * Example 1:
 * Input: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * Output: 6
 *
 * Example 2:
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Output: 42
 *
 * Initiate max_sum as the smallest possible integer and call max_gain(node = root).
 *
 * Implement max_gain(node) with a check to continue the old path/to start a new path:
 * Base case : if node is null, the max gain is 0.
 * Call max_gain recursively for the node children to compute max gain from the left and right subtrees :
 * left_gain = max(max_gain(node.left), 0) and
 * right_gain = max(max_gain(node.right), 0).
 * Now check to continue the old path or to start a new path. To start a new path would cost price_
 * newpath = node.val + left_gain + right_gain. Update max_sum if it's better to start a new path.
 * For the recursion return the max gain the node and one/zero of its subtrees could add to the current path :
 * node.val + max(left_gain, right_gain).
 */
public class BinaryTreeMaxPathSum {
    int maxSoFar = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        getMaxPath(root);
        return maxSoFar;
    }

    public int getMaxPath(TreeNode node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        int leftGain = Math.max(getMaxPath(node.left), 0);
        int rightGain = Math.max(getMaxPath(node.right), 0);

        int maxGain = Math.max(node.val, node.val + leftGain + rightGain);
        maxSoFar = Math.max(maxGain, maxSoFar);

        return Math.max(node.val+rightGain, node.val+leftGain);
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
