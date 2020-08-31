package edu.codifyme.leetcode.practice.tree;

/**
 * 450. Delete Node in a BST
 * MEDIUM: https://leetcode.com/problems/delete-node-in-a-bst/
 *
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 *
 * Basically, the deletion can be divided into two stages:
 *
 * Search for a node to remove.
 * If the node is found, delete the node.
 * Note: Time complexity should be O(height of tree).
 *
 * Example:
 *
 * root = [5,3,6,2,4,null,7]
 * key = 3
 *
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Given key to delete is 3. So we find the node with value 3 and delete it.
 *
 * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
 *
 *     5
 *    / \
 *   4   6
 *  /     \
 * 2       7
 *
 * Another valid answer is [5,2,6,null,4,null,7].
 *
 *     5
 *    / \
 *   2   6
 *    \   \
 *     4   7
 *
 * Approach:
 * delete leaf node and update pointer
 * replace one child node to current node and update pointer
 * if both left and right exists, replace the right successor to current node and update pointer
 */
public class DeleteNodeInBST {
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode parent = new TreeNode(-1);
        parent.right = root;
        delNode(root, parent, false, key);
        return parent.right;
    }

    void delNode(TreeNode node, TreeNode parent, boolean isLeft, int key) {
        if (node == null) {
            return;
        }

        if (key > node.val) {
            delNode(node.right, node, false, key);
        } else if (key < node.val) {
            delNode(node.left, node, true, key);
        } else {
            // leaf node
            if (node.left == null && node.right == null) {
                setVal(parent, isLeft, null);
            } else if (node.left == null) {
                setVal(parent, isLeft, node.right);
            } else if (node.right == null) {
                setVal(parent, isLeft, node.left);
            } else {
                // Find the successor and replace the value
                node.val = findSuccessor(node.right);
                delNode(node.right, node, false, node.val);
            }
        }
    }

    int findSuccessor(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }

        return node.val;
    }

    void setVal(TreeNode parent, boolean isLeft, TreeNode val) {
        if (isLeft) {
            parent.left = val;
        } else {
            parent.right = val;
        }
    }

    // Implementation 2:
//   // One step right and then always left
//   public int successor(TreeNode root) {
//     root = root.right;
//     while (root.left != null) root = root.left;
//     return root.val;
//   }

//   // One step left and then always right
//   public int predecessor(TreeNode root) {
//     root = root.left;
//     while (root.right != null) root = root.right;
//     return root.val;
//   }

//   public TreeNode deleteNode(TreeNode root, int key) {
//     if (root == null) return null;

//     // delete from the right subtree
//     if (key > root.val) root.right = deleteNode(root.right, key);
//     // delete from the left subtree
//     else if (key < root.val) root.left = deleteNode(root.left, key);
//     // delete the current node
//     else {
//       // the node is a leaf
//       if (root.left == null && root.right == null) root = null;
//       // the node is not a leaf and has a right child
//       else if (root.right != null) {
//         root.val = successor(root);
//         root.right = deleteNode(root.right, root.val);
//       }
//       // the node is not a leaf, has no right child, and has a left child
//       else {
//         root.val = predecessor(root);
//         root.left = deleteNode(root.left, root.val);
//       }
//     }
//     return root;
//   }

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
