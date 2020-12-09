package edu.codifyme.leetcode.practice.stacknqueue;

import java.util.Stack;

/**
 * 173. Binary Search Tree Iterator
 * MEDIUM: https://leetcode.com/problems/binary-search-tree-iterator/
 *
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 *
 * BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of
 * the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
 * boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise
 * returns false.
 * int next() Moves the pointer to the right, then returns the number at the pointer.
 * Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the
 * smallest element in the BST.
 *
 * You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order
 * traversal when next() is called.
 *
 * Example 1:
 * Input
 * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 * Output
 * [null, 3, 7, true, 9, true, 15, true, 20, false]
 *
 * Explanation
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 * bSTIterator.next();    // return 3
 * bSTIterator.next();    // return 7
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 9
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 15
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 20
 * bSTIterator.hasNext(); // return False
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 105].
 * 0 <= Node.val <= 106
 * At most 105 calls will be made to hasNext, and next.
 *
 * Follow up:
 * Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory, where h is the height of
 * the tree?
 *
 * Approach:
 * if we could simulate a controlled recursion for an inorder traversal, we wouldn't really need to use any additional
 * space other than the space used by the stack for our recursion simulation.
 *
 * So, this approach essentially uses a custom stack to simulate the inorder traversal i.e. we will be taking an
 * iterative approach to inorder traversal rather than going with the recursive approach and in doing so, we will be
 * able to easily implement the two function calls without any other additional space.
 *
 * Things however, do get a bit complicated as far as the time complexity of the two operations is concerned and that is
 * where we will spend a little bit of time to understand if this approach complies with all the asymptotic complexity
 * requirements of the question. Let's move on to the algorithm for now to look at this idea more concretely.
 */
public class BinarySearchTreeIterator {
    Stack<TreeNode> st;

    public BinarySearchTreeIterator(TreeNode root) {
        st = new Stack<>();
        moveToNextPrint(root);
    }

    public int next() {
        TreeNode item = st.pop();

        if (item.right != null) {
            moveToNextPrint(item.right);
        }

        return item.val;
    }

    public boolean hasNext() {
        return !st.isEmpty();
    }

    void moveToNextPrint(TreeNode node) {
        while (node != null) {
            st.push(node);
            node = node.left;
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
