package edu.codifyme.leetcode.interview.google.ztopfifty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1110. Delete Nodes And Return Forest
 * MEDIUM: https://leetcode.com/problems/delete-nodes-and-return-forest/
 *
 * Given the root of a binary tree, each node in the tree has a distinct value.
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 * Return the roots of the trees in the remaining forest.  You may return the result in any order.
 *
 * Example 1:
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * Output: [[1,2,null,4],[6],[7]]
 *
 * Constraints:
 * The number of nodes in the given tree is at most 1000.
 * Each node has a distinct value between 1 and 1000.
 * to_delete.length <= 1000
 * to_delete contains distinct values between 1 and 1000.
 *
 * Approach: DFS; delete nodes when recursion returns
 */
public class DeleteNodesAndReturnForest {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> delete = new HashSet<>();
        List<TreeNode> result = new ArrayList<>();

        if (null == root) {
            return result;
        }

        for (int loop = 0; loop < to_delete.length; loop++) {
            delete.add(to_delete[loop]);
        }

        erase(root, delete, result);

        if (!delete.contains(root.val)) {
            result.add(root);
        }

        return result;
    }

    boolean erase(TreeNode node, Set<Integer> delete, List<TreeNode> result) {
        if ( delete.contains(node.val) ) {
            if (null != node.left && !delete.contains(node.left.val))
                result.add(node.left);

            if (null != node.right && !delete.contains(node.right.val))
                result.add(node.right);
        }

        if ( null != node.left ) {
            if( erase(node.left, delete, result) ) {
                node.left = null;
            }
        }
        if ( null != node.right ) {
            if( erase(node.right, delete, result) ) {
                node.right = null;
            }
        }

        if ( delete.contains(node.val) ) {
            return true;
        } else {
            return false;
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
