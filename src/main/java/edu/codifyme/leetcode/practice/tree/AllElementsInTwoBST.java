package edu.codifyme.leetcode.practice.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 1305. All Elements in Two Binary Search Trees
 * MEDIUM: https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
 *
 * Given two binary search trees root1 and root2.
 * Return a list containing all the integers from both trees sorted in ascending order.
 *
 * Example 1:
 * Input: root1 = [2,1,4], root2 = [1,0,3]
 * Output: [0,1,1,2,3,4]
 *
 * Example 2:
 * Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
 * Output: [-10,0,0,1,2,5,7,10]
 *
 * Example 3:
 * Input: root1 = [], root2 = [5,1,7,0,2]
 * Output: [0,1,2,5,7]
 *
 * Example 4:
 * Input: root1 = [0,-10,10], root2 = []
 * Output: [-10,0,10]
 *
 * Example 5:
 * Input: root1 = [1,null,8], root2 = [8,1]
 * Output: [1,1,8,8]
 *
 * Constraints:
 * Each tree has at most 5000 nodes.
 * Each node's value is between [-10^5, 10^5].
 *
 * Approach 1:
 * Traverse the first tree in list1 and the second tree in list2.
 * Merge the two trees in one list and sort it.
 *
 * Approach 2: Efficient
 * Traverse the first tree in list1.
 * Traverse the second tree - list2 and Merge the sorted list list1.
 */
public class AllElementsInTwoBST {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        LinkedList<Integer> tree = new LinkedList<>();
        LinkedList<Integer> merged = new LinkedList<>();

        // Efficient O(n)
        inorder(root1, tree);
        merge(root2, merged, tree);
        merged.addAll(tree);

        // Inefficient nlog(n)
        // inorder(root1, tree);
        // inorder(root2, tree);
        // Collections.sort(tree);

        return merged;
    }

    private void inorder(TreeNode node, LinkedList<Integer> list) {
        if (node == null) return;

        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }

    private void merge(TreeNode n1, LinkedList<Integer> list, LinkedList<Integer> list1) {
        if (n1 == null) return;

        merge(n1.left, list, list1);

        while (!list1.isEmpty() && list1.peek() < n1.val) {
            list.add(list1.poll());
        }

        list.add(n1.val);

        merge(n1.right, list, list1);
    }

    // One pass iterative
//    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
//        ArrayDeque<TreeNode> stack1 = new ArrayDeque(), stack2 = new ArrayDeque();
//        List<Integer> output = new ArrayList();
//
//        while (root1 != null || root2 != null || !stack1.isEmpty() || !stack2.isEmpty()) {
//            // update both stacks
//            // by going left till possible
//            while (root1 != null) {
//                stack1.push(root1);
//                root1 = root1.left;
//            }
//            while (root2 != null) {
//                stack2.push(root2);
//                root2 = root2.left;
//            }
//
//            // Add the smallest value into output,
//            // pop it from the stack,
//            // and then do one step right
//            if (stack2.isEmpty() || !stack1.isEmpty() && stack1.getFirst().val <= stack2.getFirst().val) {
//                root1 = stack1.pop();
//                output.add(root1.val);
//                root1 = root1.right;
//            }
//            else {
//                root2 = stack2.pop();
//                output.add(root2.val);
//                root2 = root2.right;
//            }
//        }
//        return output;
//    }

    class TreeNode {
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
