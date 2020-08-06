package edu.codifyme.leetcode.interview.google.design;

/**
 * HARD:
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree
 *
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to
 * a string and this string can be deserialized to the original tree structure.
 *
 * Example:
 *
 * You may serialize the following tree:
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * as "[1,2,3,null,null,4,5]"
 * Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to
 * follow this format, so please be creative and come up with different approaches yourself.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms
 * should be stateless.
 */
public class SerializeDeserializeBinaryTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (null == root) {
            return "#";
        }

        String left = serialize(root.left);
        String right = serialize(root.right);

        return String.valueOf(root.val) + "," + left + "," + right;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        TreeNode node = deserializeUtil(data.split(","), new Counter(0));

        return node;
    }

    public TreeNode deserializeUtil(String[] data, Counter index) {
        if ( null == data || index.currIndex >= data.length ) {
            return null;
        }
        if (data[index.currIndex].equals("#") ) {
            index.currIndex++;
            return null;
        }

        int val = Integer.parseInt(data[index.currIndex]);
        index.currIndex++;

        TreeNode curr = new TreeNode(val);
        curr.left = deserializeUtil(data, index);
        curr.right = deserializeUtil(data, index);

        return curr;
    }

    class Counter {
        int currIndex;

        public Counter(int index) {
            currIndex = index;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
