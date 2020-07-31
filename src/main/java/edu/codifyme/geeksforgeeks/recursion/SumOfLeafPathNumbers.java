package edu.codifyme.geeksforgeeks.recursion;

/**
 * EASY: Sum of all the numbers that are formed from root to leaf paths
 * https://www.geeksforgeeks.org/sum-numbers-formed-root-leaf-paths/
 *
 * Given a binary tree, where every node value is a Digit from 1-9 .Find the sum of all the numbers which are formed
 * from root to leaf paths.
 * For example consider the following Binary Tree.
 *
 *            6
 *        /      \
 *      3          5
 *    /   \          \
 *   2     5          4
 *       /   \
 *      7     4
 *   There are 4 leaves, hence 4 root to leaf paths:
 *    Path                    Number
 *   6->3->2                   632
 *   6->3->5->7               6357
 *   6->3->5->4               6354
 *   6->5>4                    654
 * Answer = 632 + 6357 + 6354 + 654 = 13997
 *
 * Solution:
 * Use recursion (pre order traversal)
 * Go down and multiply current Num with 10 and pass down
 * At every leaf level, add the passing number to the total sum
 */
public class SumOfLeafPathNumbers {
    Node root;

    // Returns sum of all root to leaf paths. The first parameter is
    // root of current subtree, the second parameter is value of the
    // number formed by nodes from root to this node
    int treePathsSumUtil(Node node, int val)
    {
        // Base case
        if (node == null)
            return 0;

        // Update val
        val = (val * 10 + node.data);

        // if current node is leaf, return the current value of val
        if (node.left == null && node.right == null)
            return val;

        // recur sum of values for left and right subtree
        return treePathsSumUtil(node.left, val)
                + treePathsSumUtil(node.right, val);
    }

    // A wrapper function over treePathsSumUtil()
    int treePathsSum(Node node)
    {
        // Pass the initial value as 0 as there is nothing above root
        return treePathsSumUtil(node, 0);
    }

    // A binary tree node
    class Node
    {
        int data;
        Node left, right;

        Node(int item)
        {
            data = item;
            left = right = null;
        }
    }
}
