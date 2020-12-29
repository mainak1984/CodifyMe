package edu.codifyme.leetcode.practice.tree;

/**
 * 1457. Pseudo-Palindromic Paths in a Binary Tree
 * MEDIUM: https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/
 *
 * Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be
 * pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
 *
 * Return the number of pseudo-palindromic paths going from the root node to leaf nodes.
 *
 * Example 1:
 * Input: root = [2,3,1,3,1,null,1]
 * Output: 2
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to
 * leaf nodes: the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and
 * green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the
 * green path [2,1,1] can be rearranged in [1,2,1] (palindrome).
 *
 * Example 2:
 * Input: root = [2,1,1,1,3,null,null,null,null,null,1]
 * Output: 1
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to
 * leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is
 * pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).
 *
 * Example 3:
 * Input: root = [9]
 * Output: 1
 *
 * Constraints:
 * The given binary tree will have between 1 and 10^5 nodes.
 * Node values are digits from 1 to 9.
 *
 * Approach:
 * The problem consists of two subproblems:
 * - Traverse the tree to build all root-to-leaf paths.
 * - For each root-to-leaf path, check if it's a pseudo-palindromic path or not.
 *
 * Recursive preorder traversal is extremely simple: follow Root->Left->Right direction, i.e., do all the business with
 * the node (i.e., update the current path and the counter), and then do the recursive calls for the left and right
 * child nodes.
 *
 * How to check if the path is pseudo-palindromic or not
 * The straightforward way is to save each root-to-leaf path into a list and then to check each digit for parity.
 * This method requires to keep each root-to-leaf path, and that becomes space-consuming for the large trees. To save
 * the space, let's compute the parity on-the-fly using bitwise operators.
 * The idea is to keep the frequency of digit 1 in the first bit, 2 in the second bit, etc: path ^= (1 << node.val).
 */
public class PseudoPalindromicPathInBinaryTree {
    int count = 0;

    public void preorder(TreeNode node, int path) {
        if (node != null) {
            // compute occurences of each digit
            // in the corresponding register
            path = path ^ (1 << node.val);
            // if it's a leaf check if the path is pseudo-palindromic
            if (node.left == null && node.right == null) {
                // check if at most one digit has an odd frequency
                if ((path & (path - 1)) == 0) {
                    ++count;
                }
            }
            preorder(node.left, path);
            preorder(node.right, path) ;
        }
    }

    public int pseudoPalindromicPaths (TreeNode root) {
        preorder(root, 0);
        return count;
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
