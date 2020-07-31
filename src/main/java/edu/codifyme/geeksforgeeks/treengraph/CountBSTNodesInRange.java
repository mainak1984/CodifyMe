package edu.codifyme.geeksforgeeks.treengraph;

/**
 * EASY: Count BST nodes that lie in a given range
 * https://www.geeksforgeeks.org/count-bst-nodes-that-are-in-a-given-range/
 *
 * Given a Binary Search Tree (BST) and a range, count number of nodes that lie in the given range.
 * Examples:
 *
 * Input:
 *         10
 *       /    \
 *     5       50
 *    /       /  \
 *  1       40   100
 * Range: [5, 45]
 *
 * Output:  3
 * There are three nodes in range, 5, 10 and 40
 *
 * Approach:
 * Simple DFS and start counting 1 more if node is in range.
 */
public class CountBSTNodesInRange {
    // Returns count of nodes in BST in
    // range [low, high]
    int getCount(Node node, int low, int high)
    {
        // Base Case
        if(node == null)
            return 0;

        // If current node is in range, then
        // include it in count and recur for
        // left and right children of it
        if(node.data >= low && node.data <= high)
            return 1 + this.getCount(node.left, low, high)+
                    this.getCount(node.right, low, high);

            // If current node is smaller than low,
            // then recur for right child
        else if(node.data < low)
            return this.getCount(node.right, low, high);

            // Else recur for left child
        else
            return this.getCount(node.left, low, high);
    }

    static class Node {
        int data;
        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }
}
