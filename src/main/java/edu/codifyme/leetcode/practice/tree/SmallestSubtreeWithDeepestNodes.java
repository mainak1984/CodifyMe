package edu.codifyme.leetcode.practice.tree;

/**
 * 865. Smallest Subtree with all the Deepest Nodes
 * MEDIUM: https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
 *
 * Given the root of a binary tree, the depth of each node is the shortest distance to the root.
 * Return the smallest subtree such that it contains all the deepest nodes in the original tree.
 * A node is called the deepest if it has the largest depth possible among any node in the entire tree.
 * The subtree of a node is tree consisting of that node, plus the set of all descendants of that node.
 *
 * Note: This question is the same as 1123: https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4]
 * Output: [2,7,4]
 * Explanation: We return the node with value 2, colored in yellow in the diagram.
 * The nodes coloured in blue are the deepest nodes of the tree.
 * Notice that nodes 5, 3 and 2 contain the deepest nodes in the tree but node 2 is the smallest subtree among them, so
 * we return it.
 *
 * Example 2:
 * Input: root = [1]
 * Output: [1]
 * Explanation: The root is the deepest node in the tree.
 *
 * Example 3:
 * Input: root = [0,1,3,null,2]
 * Output: [2]
 * Explanation: The deepest node in the tree is 2, the valid subtrees are the subtrees of nodes 2, 1 and 0 but the
 * subtree of node 2 is the smallest.
 *
 * Constraints:
 * The number of nodes in the tree will be in the range [1, 500].
 * 0 <= Node.val <= 500
 * The values of the nodes in the tree are unique.
 *
 * Approach:
 * Intuition:
 * We can combine both depth first searches in Approach #1 into an approach that does both steps in one pass. We will
 * have some function dfs(node) that returns both the answer for this subtree, and the distance from node to the deepest
 * nodes in this subtree.
 *
 * Algorithm:
 * The Result (on some subtree) returned by our (depth-first search) recursion will have two parts:
 *  - Result.node: the largest depth node that is equal to or an ancestor of all the deepest nodes of this subtree.
 *  - Result.dist: the number of nodes in the path from the root of this subtree, to the deepest node in this subtree.
 *
 * We can calculate these answers disjointly for dfs(node):
 *  - To calculate the Result.node of our answer:
 *      - If one childResult has deeper nodes, then childResult.node will be the answer.
 *      - If they both have the same depth nodes, then node will be the answer.
 *  - The Result.dist of our answer is always 1 more than the largest childResult.dist we have.
 */
public class SmallestSubtreeWithDeepestNodes {
    // Approach 1: efficient
    public TreeNode subtreeWithAllDeepest1(TreeNode root) {
        return dfs(root).node;
    }

    // Return the result of the subtree at this node.
    public Result dfs(TreeNode node) {
        if (node == null) return new Result(null, 0);
        Result L = dfs(node.left),
                R = dfs(node.right);
        if (L.dist > R.dist) return new Result(L.node, L.dist + 1);
        if (L.dist < R.dist) return new Result(R.node, R.dist + 1);
        return new Result(node, L.dist + 1);
    }

    class Result {
        TreeNode node;
        int dist;
        Result(TreeNode n, int d) {
            node = n;
            dist = d;
        }
    }

    // Approach 2: Self implementation
    int left;
    int right;
    int maxDepth = 0;

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        traverseNodes(root, 1);
        return lca(root, left, right);
    }

    void traverseNodes(TreeNode node, int height) {
        if (node == null) {
            return;
        }

        if (height > maxDepth) {
            maxDepth = height;
            left = node.val;
            right = node.val;
        } else if (height == maxDepth) {
            right = node.val;
        }

        traverseNodes(node.left, height+1);
        traverseNodes(node.right, height+1);
    }

    TreeNode lca(TreeNode node, int left, int right) {
        if (node == null) {
            return null;
        }

        if (node.val == left || node.val == right) {
            return node;
        }

        TreeNode leftAvailable = lca(node.left, left, right);
        TreeNode rightAvailable = lca(node.right, left, right);

        if (leftAvailable != null && rightAvailable != null) {
            return node;
        }

        return (leftAvailable != null)? leftAvailable: rightAvailable;
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
