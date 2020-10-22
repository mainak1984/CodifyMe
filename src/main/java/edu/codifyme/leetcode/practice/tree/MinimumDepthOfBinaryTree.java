package edu.codifyme.leetcode.practice.tree;

import javafx.util.Pair;

import java.util.LinkedList;

/**
 * 111. Minimum Depth of Binary Tree
 * EASY: https://leetcode.com/problems/minimum-depth-of-binary-tree/
 *
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * Note: A leaf is a node with no children.
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 2
 *
 * Example 2:
 * Input: root = [2,null,3,null,4,null,5,null,6]
 * Output: 5
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 105].
 * -1000 <= Node.val <= 1000
 *
 * Approach:
 * DFS and use pruning of min distance seen
 * BFS and break when first leaf node found
 */
public class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        else {
            queue.add(new Pair(root, 1));
        }

        int current_depth = 0;
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> current = queue.poll();
            root = current.getKey();
            current_depth = current.getValue();
            if ((root.left == null) && (root.right == null)) {
                break;
            }
            if (root.left != null) {
                queue.add(new Pair(root.left, current_depth + 1));
            }
            if (root.right != null) {
                queue.add(new Pair(root.right, current_depth + 1));
            }
        }
        return current_depth;
    }

//     int min = Integer.MAX_VALUE;
//     public int minDepth(TreeNode root) {
//         min = Integer.MAX_VALUE;

//         if (null == root) {
//             min = 0;
//         } else {
//             minDepth(root, 1);
//         }

//         return min;
//     }

//     void minDepth(TreeNode root, int depth) {
//         if (root.left == null && root.right == null) {
//             min = Math.min(min, depth);
//             return;
//         }

//         if (depth < min && root.left != null) {
//             minDepth(root.left, depth+1);
//         }

//         if (depth < min && root.right != null) {
//             minDepth(root.right, depth+1);
//         }
//     }

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
