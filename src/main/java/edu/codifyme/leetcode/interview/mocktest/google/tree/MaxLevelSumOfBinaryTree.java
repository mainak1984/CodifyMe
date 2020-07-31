package edu.codifyme.leetcode.interview.mocktest.google.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * MEDIUM: Maximum Level Sum of a Binary Tree
 * https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
 *
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 * Return the smallest level X such that the sum of all the values of nodes at level X is maximal.
 *
 * Example 1:
 * Input: [1,7,0,7,-8,null,null]
 * Output: 2
 * Explanation:
 * Level 1 sum = 1.
 * Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1.
 * So we return the level with the maximum sum which is level 2.
 *
 * Note:
 * The number of nodes in the given tree is between 1 and 10^4.
 * -10^5 <= node.val <= 10^5
 *
 * Approach:
 * Level order traversal; check level sum with max
 *
 * Approach 2: (faster)
 * Inorder traversal (DFS) and add to sum of each level (global array). Run through sum array to find the level
 */
public class MaxLevelSumOfBinaryTree {
    public int maxLevelSum(TreeNode root) {
        int max = Integer.MIN_VALUE;
        int currLevel = 0;
        int level = 0;
        int levelSum;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Queue<TreeNode> tempQueue = new LinkedList<>();
            levelSum = 0;
            currLevel++;

            while (!queue.isEmpty()) {
                TreeNode child = queue.poll();

                if (null != child.left) {
                    tempQueue.add(child.left);
                }

                if (null != child.right) {
                    tempQueue.add(child.right);
                }
                levelSum += child.val;
            }

            if (max < levelSum) {
                max = levelSum;
                level = currLevel;
            }

            queue = tempQueue;
        }

        return level;
    }

    // Alternate approach: DFS
//    int n = 10000;
//    int[] levelSum = new int[n];
//
//    public void inorder(TreeNode node, int level) {
//        if (node != null) {
//            inorder(node.left, level + 1);
//            levelSum[level] += node.val;
//            inorder(node.right, level + 1);
//        }
//    }
//
//    public int maxLevelSum(TreeNode root) {
//        inorder(root, 1);
//
//        int maxIdx = 0;
//        for (int i = 0; i < n; ++i)
//            maxIdx = levelSum[i] > levelSum[maxIdx] ? i : maxIdx;
//        return maxIdx;
//    }

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
