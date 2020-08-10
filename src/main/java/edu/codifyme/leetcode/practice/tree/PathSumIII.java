package edu.codifyme.leetcode.practice.tree;

import java.util.HashMap;

/**
 * 437. Path Sum III
 * MEDIUM: https://leetcode.com/problems/path-sum-iii
 *
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent
 * nodes to child nodes).
 *
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 *
 * Example:
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * Return 3. The paths that sum to 8 are:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 *
 *
 * Algorithm:
 *
 * > Let's initialize tree paths counter count = 0, and the hashmap h "prefix sum -> how many times was it seen so far".
 * > One could parse the tree using recursive preorder traversal: node -> left -> right:
 * preorder(node: TreeNode, curr_sum: int) -> None. This function takes two arguments: a tree node and a prefix sum
 * before that node. To start the recursion chain, one should call preorder(root, 0).
 * >>> The first thing is to update the current prefix sum by adding the value of the current node: curr_sum += node.val
 * >>> Now one could update the counter. One should consider two situations.
 *     In situation 1, the tree path with the target sum starts from the root. That means the current prefix sum is
 * equal to the target sum curr_sum == k, so one should increase the counter by 1: count += 1.
 *     In situation 2, the tree path with the target sum starts somewhere downwards. That means we should add to the
 * counter the number of times we have seen the prefix sum curr_sum - target so far: count += h[curr_sum - target].
 *     The logic is simple: the current prefix sum is curr_sum, and several elements before the prefix sum was curr_sum
 *  - target. All the elements in between sum up to curr_sum - (curr_sum - target) = target.
 * >>> Now it's time to update the hashmap: h[curr_sum] += 1.
 * >>> Let's parse left and right subtrees: preorder(node.left, curr_sum), preorder(node.right, curr_sum).
 * >>> Now the current subtree is processed. It's time to remove the current prefix sum from the hashmap, in order not
 * to blend the parallel subtrees: h[curr_sum] -= 1.
 * > Now the preorder traversal is done, and the counter is updated. Return it.
 */
public class PathSumIII {
    int count = 0;
    int k;
    HashMap<Integer, Integer> h = new HashMap();

    public void preorder(TreeNode node, int currSum) {
        if (node == null)
            return;

        // current prefix sum
        currSum += node.val;

        // here is the sum we're looking for
        if (currSum == k)
            count++;

        // number of times the curr_sum − k has occured already,
        // determines the number of times a path with sum k
        // has occured upto the current node
        count += h.getOrDefault(currSum - k, 0);

        // add the current sum into hashmap
        // to use it during the child nodes processing
        h.put(currSum, h.getOrDefault(currSum, 0) + 1);

        // process left subtree
        preorder(node.left, currSum);
        // process right subtree
        preorder(node.right, currSum);

        // remove the current sum from the hashmap
        // in order not to use it during
        // the parallel subtree processing
        h.put(currSum, h.get(currSum) - 1);
    }

    public int pathSum(TreeNode root, int sum) {
        k = sum;
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

    // Approach 2
//     int count = 0;

//     public int pathSum(TreeNode root, int sum) {
//         if (null == root) {
//             return 0;
//         }
//         dfs(root, sum, new LinkedList<Integer>(), 0);
//         return count;
//     }

//     void dfs(TreeNode node, int target, LinkedList<Integer> list, int runningSum) {
//         list.add(node.val);
//         runningSum += node.val;

//         LinkedList<Integer> currList = new LinkedList<Integer>(list);
//         int currSum = runningSum;

//         while (currList.size() > 0) {
//             if (currSum == target && currList.size() > 0) {
//                 count++;
//             }

//             currSum -= currList.poll();
//         }

//         if (node.left != null) {
//             dfs(node.left, target, list, runningSum);
//         }

//         if (node.right != null) {
//             dfs(node.right, target, list, runningSum);
//         }

//         list.removeLast();
//     }
}
