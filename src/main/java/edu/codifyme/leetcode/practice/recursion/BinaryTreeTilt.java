package edu.codifyme.leetcode.practice.recursion;

/**
 * 563. Binary Tree Tilt
 * EASY: https://leetcode.com/problems/binary-tree-tilt/
 *
 * Given the root of a binary tree, return the sum of every tree node's tilt.
 *
 * The tilt of a tree node is the absolute difference between the sum of all left subtree node values and all right
 * subtree node values. If a node does not have a left child, then the sum of the left subtree node values is treated as
 * 0. The rule is similar if there the node does not have a right child.
 *
 * Example 1:
 * Input: root = [1,2,3]
 * Output: 1
 * Explanation:
 * Tilt of node 2 : |0-0| = 0 (no children)
 * Tilt of node 3 : |0-0| = 0 (no children)
 * Tile of node 1 : |2-3| = 1 (left subtree is just left child, so sum is 2; right subtree is just right child, so sum
 * is 3)
 * Sum of every tilt : 0 + 0 + 1 = 1
 *
 * Example 2:
 * Input: root = [4,2,9,3,5,null,7]
 * Output: 15
 * Explanation:
 * Tilt of node 3 : |0-0| = 0 (no children)
 * Tilt of node 5 : |0-0| = 0 (no children)
 * Tilt of node 7 : |0-0| = 0 (no children)
 * Tilt of node 2 : |3-5| = 2 (left subtree is just left child, so sum is 3; right subtree is just right child, so sum
 * is 5)
 * Tilt of node 9 : |0-7| = 7 (no left child, so sum is 0; right subtree is just right child, so sum is 7)
 * Tilt of node 4 : |(3+5+2)-(9+7)| = |10-16| = 6 (left subtree values are 3, 5, and 2, which sums to 10; right subtree
 * values are 9 and 7, which sums to 16)
 * Sum of every tilt : 0 + 0 + 0 + 2 + 7 + 6 = 15
 *
 * Example 3:
 * Input: root = [21,7,14,1,1,2,2,3,3]
 * Output: 9
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 104].
 * -1000 <= Node.val <= 1000
 *
 * Approach:
 *  in order to calculate the tilt value for a node, we need to calculate the sum of its left and right subtrees
 *  respectively.
 * Let us first implement the function valueSum(node) which returns the sum of values for all nodes starting from the
 * given node, which can be summarized with the following recursive formula:
 * valueSum(node)=node.val+valueSum(node.left)+valueSum(node.right)
 *
 * Furthermore, the tilt value of a node also depends on the value sum of its left and right subtrees, as follows:
 * tilt(node)=∣valueSum(node.left)−valueSum(node.right)∣
 *
 * Intuitively, we could combine the above calculations within a single recursive function. In this way, we only need to
 * traverse each node once and only once.
 * More specifically, we will traverse the tree in the post-order DFS, i.e. we visit a node's left and right subtrees
 * before processing the value of the current node.
 */
public class BinaryTreeTilt {
    int sum;
    public int findTilt(TreeNode root) {
        sum = 0;
        traverse(root);
        return sum;
    }

    int traverse(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftSum = traverse(node.left);
        int rightSum = traverse(node.right);
        sum += Math.abs(leftSum - rightSum);

        return node.val + leftSum + rightSum;
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
