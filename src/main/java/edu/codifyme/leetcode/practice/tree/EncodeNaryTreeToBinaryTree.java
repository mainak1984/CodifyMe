package edu.codifyme.leetcode.practice.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 431. Encode N-ary Tree to Binary Tree
 * HARD: https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/
 *
 * Design an algorithm to encode an N-ary tree into a binary tree and decode the binary tree to get the original N-ary
 * tree. An N-ary tree is a rooted tree in which each node has no more than N children. Similarly, a binary tree is a
 * rooted tree in which each node has no more than 2 children. There is no restriction on how your encode/decode
 * algorithm should work. You just need to ensure that an N-ary tree can be encoded to a binary tree and this binary
 * tree can be decoded to the original N-nary tree structure.
 *
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by
 * the null value (See following example).
 *
 * For example, you may encode the following 3-ary tree to a binary tree in this way:
 *
 * Input: root = [1,null,3,2,4,null,5,6]
 * Note that the above is just an example which might or might not work. You do not necessarily need to follow this
 * format, so please be creative and come up with different approaches yourself.
 *
 * Constraints:
 * The height of the n-ary tree is less than or equal to 1000
 * The total number of nodes is between [0, 10^4]
 * Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 *
 * Approach:
 * algorithm can be summarized in two steps. We use the above N-ary tree as an example for demonstration.
 *
 * Step 1). Link all siblings together, like a singly-linked list.
 * Each node in the original N-ary tree would correspond uniquely to a node in the resulting binary tree.
 * In the first step, we first chain up all the sibling nodes together, i.e. nodes that share the same parent. By
 * chaining up, we would link the nodes via either left or right child pointers of the binary tree node. Here we choose
 * to do with the right pointer.
 *
 * Step 2). Link the head of the obtained list of siblings with its parent node.
 * Now that the siblings are chained up, we just need to link this sibling list with their parent node.
 * As one can see, we don't have to link each one of the siblings to its parent, and we cannot do so either, since we
 * only got two pointers for a node in binary tree. It suffices to pick one of the siblings. Naturally, we could link
 * the head of the list with its parent node.
 *
 * Before one notices, after the above two steps, we have already converted the N-ary tree to a binary tree !
 */
public class EncodeNaryTreeToBinaryTree {

    //Efficient implementation
    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
        if (root == null) {
            return null;
        }

        TreeNode newRoot = new TreeNode(root.val);

        // Encode the first child of n-ary node to the left node of binary tree.
        if (root.children.size() > 0) {
            Node firstChild = root.children.get(0);
            newRoot.left = this.encode(firstChild);
        }

        // Encoding the rest of the sibling nodes.
        TreeNode sibling = newRoot.left;
        for (int i = 1; i < root.children.size(); ++i) {
            sibling.right = this.encode(root.children.get(i));
            sibling = sibling.right;
        }

        return newRoot;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }

        Node newRoot = new Node(root.val, new ArrayList<Node>());

        // Decoding all the children nodes
        TreeNode sibling = root.left;
        while (sibling != null) {
            newRoot.children.add(this.decode(sibling));
            sibling = sibling.right;
        }

        return newRoot;
    }

    // Another implementation:
    // Encodes an n-ary tree to a binary tree.
//    public TreeNode encode(Node root) {
//        if (null == root) {
//            return null;
//        }
//
//        TreeNode treeRoot = new TreeNode(root.val);
//        encode(root, treeRoot, new ArrayList<Node>(), 0);
//
//        return treeRoot;
//    }
//
//    void encode(Node nNode, TreeNode tNode, List<Node> peerNodes, int peerNodeStartingIndex ) {
//
//        if (nNode.children != null && nNode.children.size() > 0) {
//            TreeNode left = new TreeNode(nNode.children.get(0).val);
//            tNode.left = left;
//            encode(nNode.children.get(0), left, nNode.children, 1);
//        }
//
//        if (peerNodes.size() > peerNodeStartingIndex) {
//            TreeNode right = new TreeNode(peerNodes.get(peerNodeStartingIndex).val);
//            tNode.right = right;
//            encode(peerNodes.get(peerNodeStartingIndex), right, peerNodes, peerNodeStartingIndex+1);
//        }
//    }
//
//    // Decodes your binary tree to an n-ary tree.
//    public Node decode(TreeNode root) {
//        if (null == root) {
//            return null;
//        }
//
//        Node nodeRoot = new Node(root.val, new LinkedList<Node>());
//        if (root.left != null) {
//            Node nNodeLeft = new Node(root.left.val, new LinkedList<Node>());
//            decode(root.left, nNodeLeft, nodeRoot, true);
//        }
//        return nodeRoot;
//    }
//
//    List<Node> decode(TreeNode tNode, Node nNode, Node nNodeParent, boolean isLeftChild) {
//        if (tNode.left != null) {
//            Node nNodeLeft = new Node(tNode.left.val, new LinkedList<Node>());
//            decode(tNode.left, nNodeLeft, nNode, true);
//        }
//
//        List<Node> peers = new LinkedList<>();
//        peers.add(nNode);
//        if (tNode.right != null) {
//            Node nNodeRight = new Node(tNode.right.val, new LinkedList<Node>());
//            List<Node> otherPeers = decode(tNode.right, nNodeRight, nNode, false);
//            peers.addAll(otherPeers);
//        }
//
//        if (isLeftChild) {
//            nNodeParent.children = peers;
//            return null;
//        } else {
//            return peers;
//        }
//    }

    class Node {
        public int val;
        public List<Node> children;
        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
