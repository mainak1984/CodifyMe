package edu.codifyme.geeksforgeeks.treengraph;

import java.util.HashSet;

/**
 * MEDIUM: Check if a Binary Tree contains duplicate subtrees of size 2 or more
 * https://www.geeksforgeeks.org/check-binary-tree-contains-duplicate-subtrees-size-2/
 *
 * Given a Binary Tree, check whether the Binary tree contains a duplicate sub-tree of size 2 or more.
 * Note : Two same leaf nodes are not considered as subtree size of a leaf node is one.
 *
 * Input :  Binary Tree
 *                A
 *              /    \
 *            B        C
 *          /   \       \
 *         D     E       B
 *                      /  \
 *                     D    E
 * Output : Yes
 * Asked in : Google Interview
 *
 * Solution:
 * Do inorder traversal, make a sliding window of 2 and apply hashing
 * If hash matches, output yes
 */
public class DuplicateSubtreeinBinaryTree {
    static char MARKER = '$';

    //Function to find if the Binary Tree contains duplicate
    //subtrees of size 2 or more
    public static String dupSub(Node root)
    {
        HashSet<String> subtrees=new HashSet<>();
        return dupSubUtil(root,subtrees);
    }

    // This function returns empty string if tree
    // contains a duplicate subtree of size 2 or more.
    public static String dupSubUtil(Node root, HashSet<String> subtrees)
    {
        String s = "";

        // If current node is NULL, return marker
        if (root == null)
            return s + MARKER;

        // If left subtree has a duplicate subtree.
        String lStr = dupSubUtil(root.left,subtrees);
        if (lStr.equals(s))
            return s;

        // Do same for right subtree
        String rStr = dupSubUtil(root.right,subtrees);
        if (rStr.equals(s))
            return s;

        // Serialize current subtree
        s = s + root.data + lStr + rStr;

        // If current subtree already exists in hash
        // table. [Note that size of a serialized tree
        // with single node is 3 as it has two marker
        // nodes.
        if (s.length() > 3 && subtrees.contains(s))
            return "";

        subtrees.add(s);
        return s;
    }

    class Node {
        int data;
        Node left,right;
        Node(int data)
        {
            this.data=data;
        }
    };
}
