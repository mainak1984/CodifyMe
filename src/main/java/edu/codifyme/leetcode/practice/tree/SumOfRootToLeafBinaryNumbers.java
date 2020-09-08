package edu.codifyme.leetcode.practice.tree;

/**
 * 1022. Sum of Root To Leaf Binary Numbers
 * EASY: https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/
 *
 * Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with the
 * most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary,
 * which is 13.
 *
 * For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.
 * Return the sum of these numbers.
 *
 * Example 1:
 * Input: [1,0,1,0,1,0,1]
 * Output: 22
 * Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 *
 * Note:
 *
 * The number of nodes in the tree is between 1 and 1000.
 * node.val is 0 or 1.
 * The answer will not exceed 2^31 - 1.
 *
 * Approach:
 * DFS and check when we have reached a leaf node, add the value to total.
 *
 * Bitwise Trick:
 * If you work with decimal representation, the conversion of 1->2 into 12 is easy. You start from curr_number = 1, then
 * shift one register to the left and add the next digit: curr_number = 1 * 10 + 2 = 12.
 * If you work with binaries 1 -> 1 -> 3, it's the same. You start from curr_number = 1, then shift one register to the
 * left and add the next digit: curr_number = (1 << 1) | 1 = 3.
 */
public class SumOfRootToLeafBinaryNumbers {
    int sum;
    public int sumRootToLeaf(TreeNode root) {
        sum = 0;
        sumRootToLeaf(root, 0);
        return sum;
    }

    void sumRootToLeaf(TreeNode node, int number) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            sum += number << 1 | node.val;
            return;
        }

        sumRootToLeaf(node.left, number << 1 | node.val);
        sumRootToLeaf(node.right, number << 1 | node.val);
    }
}
