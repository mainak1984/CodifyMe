package edu.codifyme.leetcode.interview.google.treesngrapsh;

/**
 * 222. Count Complete Tree Nodes
 * MEDIUM: https://leetcode.com/problems/count-complete-tree-nodes
 * Editor's choice: Frequently asked in Google Phone Interview
 *
 * Given a complete binary tree, count the number of nodes.
 *
 * Note:
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last
 * level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Example:
 * Input:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 *
 * Output: 6
 *
 * there are two questions:
 * How many nodes in the last level have to be checked?
 * What is the best time performance for such a check?
 *
 * Let's start from the first question. It's a complete tree, and hence all nodes in the last level are as far left as
 * possible. That means that instead of checking the existence of all 2^d possible leafs, one could use binary search
 * and check log(2^d) = d leafs only.
 *
 * Let's move to the second question, and enumerate potential nodes in the last level from 0 to 2^d − 1. How to check
 * if the node number idx exists? Let's use binary search again to reconstruct the sequence of moves from root to idx
 * node. For example, idx = 4. idx is in the second half of nodes 0,1,2,3,4,5,6,7 and hence the first move is to the
 * right. Then idx is in the first half of nodes 4,5,6,7 and hence the second move is to the left. The idx is in the
 * first half of nodes 4,5 and hence the next move is to the left. The time complexity for one check is O(d).
 *
 * 1 and 2 together result in O(d) checks, each check at a price of O(d). That means that the overall time complexity
 * would be O(d^2)
 */
public class CountCompleteTreeNodes {

    public int countNodes(TreeNode root) {
        TreeNode head = root;
        int leftLen = 0;
        int rightLen = 0;
        int maxSoFar;

        if (null == root) {
            return 0;
        }

        while (head != null) {
            leftLen += 1;
            head = head.left;
        }

        head = root;
        while (head != null) {
            rightLen += 1;
            head = head.right;
        }

        if ( leftLen == rightLen ) {
            return (int)Math.pow(2, rightLen) - 1;
        } else {
            maxSoFar = (int)Math.pow(2, rightLen);

            int left = (int)Math.pow(2, rightLen);
            int right = (int)Math.pow(2, leftLen) - 2;
            while (left <= right) {
                int mid = left + (right - left)/2;
                String binary = Integer.toBinaryString(mid);

                if (getLastElement(root, binary, 0)) {
                    maxSoFar = Math.max(maxSoFar, mid);
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return maxSoFar;
    }

    public boolean getLastElement(TreeNode node, String binary, int position) {
        if ( null == node ) {
            return false;
        }
        if ( null == node.left &&  null == node.right ) {
            if (position == binary.length() - 1 ) {
                return true;
            } else {
                return false;
            }
        }

        if ( binary.charAt(position+1) == '0' ) {
            return getLastElement(node.left, binary, position + 1);
        } else {
            return getLastElement(node.right, binary, position + 1);
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

    // Approach 2
    // Return tree depth in O(d) time.
//    public int computeDepth(TreeNode node) {
//        int d = 0;
//        while (node.left != null) {
//            node = node.left;
//            ++d;
//        }
//        return d;
//    }

    // Last level nodes are enumerated from 0 to 2**d - 1 (left -> right).
    // Return True if last level node idx exists.
    // Binary search with O(d) complexity.
//    public boolean exists(int idx, int d, TreeNode node) {
//        int left = 0, right = (int)Math.pow(2, d) - 1;
//        int pivot;
//        for(int i = 0; i < d; ++i) {
//            pivot = left + (right - left) / 2;
//            if (idx <= pivot) {
//                node = node.left;
//                right = pivot;
//            }
//            else {
//                node = node.right;
//                left = pivot + 1;
//            }
//        }
//        return node != null;
//    }
//
//    public int countNodes(TreeNode root) {
//        // if the tree is empty
//        if (root == null) return 0;
//
//        int d = computeDepth(root);
//        // if the tree contains 1 node
//        if (d == 0) return 1;
//
//        // Last level nodes are enumerated from 0 to 2**d - 1 (left -> right).
//        // Perform binary search to check how many nodes exist.
//        int left = 1, right = (int)Math.pow(2, d) - 1;
//        int pivot;
//        while (left <= right) {
//            pivot = left + (right - left) / 2;
//            if (exists(pivot, d, root)) left = pivot + 1;
//            else right = pivot - 1;
//        }
//
//        // The tree contains 2**d - 1 nodes on the first (d - 1) levels
//        // and left nodes on the last level.
//        return (int)Math.pow(2, d) - 1 + left;
//    }
}
