package edu.codifyme.leetcode.interview.unordered;

/**
 * 572. Subtree of Another Tree
 * EASY: https://leetcode.com/problems/subtree-of-another-tree/
 *
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
 *
 * Example 1:
 * Given tree s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * Return true, because t has the same structure and node values with a subtree of s.
 * Example 2:
 * Given tree s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * Return false.
 */
public class SubtreeOfTree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            if (t == null) {
                return true;
            } else {
                return false;
            }
        }

        if (isMatching(s, t)) {
            return true;
        } else if ( isSubtree(s.left, t) ) {
            return true;
        } else if ( isSubtree(s.right, t) ) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isMatching(TreeNode s, TreeNode t) {
        if (s == null) {
            if (t == null) {
                return true;
            } else {
                return false;
            }
        } else if (t == null) {
            return false;
        }

        if ( s.val != t.val) {
            return false;
        } else {
            return isMatching(s.left, t.left) && isMatching(s.right, t.right);
        }
    }

    // Definition for a binary tree node.
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
