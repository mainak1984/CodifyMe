package edu.codifyme.leetcode.practice.design;

/**
 * 705. Design HashSet
 * EASY: https://leetcode.com/problems/design-hashset/
 *
 * Design a HashSet without using any built-in hash table libraries.
 *
 * To be specific, your design should include these functions:
 *
 * add(value): Insert a value into the HashSet.
 * contains(value) : Return whether the value exists in the HashSet or not.
 * remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.
 *
 * Example:
 * MyHashSet hashSet = new MyHashSet();
 * hashSet.add(1);
 * hashSet.add(2);
 * hashSet.contains(1);    // returns true
 * hashSet.contains(3);    // returns false (not found)
 * hashSet.add(2);
 * hashSet.contains(2);    // returns true
 * hashSet.remove(2);
 * hashSet.contains(2);    // returns false (already removed)
 *
 * Note:
 * All values will be in the range of [0, 1000000].
 * The number of operations will be in the range of [1, 10000].
 * Please do not use the built-in HashSet library.
 *
 * Approach:
 * Array for main buckets, BST for hash collisions
 * Used prime no. 769 for hash function
 */
public class DesignHashSet {
    private Bucket[] bucketArray;
    private int keyRange;

    /** Initialize your data structure here. */
    public DesignHashSet() {
        this.keyRange = 769;
        this.bucketArray = new Bucket[this.keyRange];
        for (int i = 0; i < this.keyRange; ++i)
            this.bucketArray[i] = new Bucket();
    }

    protected int _hash(int key) {
        return (key % this.keyRange);
    }

    public void add(int key) {
        int bucketIndex = this._hash(key);
        this.bucketArray[bucketIndex].insert(key);
    }

    public void remove(int key) {
        int bucketIndex = this._hash(key);
        this.bucketArray[bucketIndex].delete(key);
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int bucketIndex = this._hash(key);
        return this.bucketArray[bucketIndex].exists(key);
    }
}


class Bucket {
    private BSTree tree;

    public Bucket() {
        tree = new BSTree();
    }

    public void insert(Integer key) {
        this.tree.root = this.tree.insertIntoBST(this.tree.root, key);
    }

    public void delete(Integer key) {
        this.tree.root = this.tree.deleteNode(this.tree.root, key);
    }

    public boolean exists(Integer key) {
        TreeNode node = this.tree.searchBST(this.tree.root, key);
        return (node != null);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class BSTree {
    TreeNode root = null;

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || val == root.val)
            return root;

        return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);

        if (val > root.val)
            // insert into the right subtree
            root.right = insertIntoBST(root.right, val);
        else if (val == root.val)
            // skip the insertion
            return root;
        else
            // insert into the left subtree
            root.left = insertIntoBST(root.left, val);
        return root;
    }

    /*
     * One step right and then always left
     */
    public int successor(TreeNode root) {
        root = root.right;
        while (root.left != null)
            root = root.left;
        return root.val;
    }

    /*
     * One step left and then always right
     */
    public int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null)
            root = root.right;
        return root.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;

        // delete from the right subtree
        if (key > root.val)
            root.right = deleteNode(root.right, key);
            // delete from the left subtree
        else if (key < root.val)
            root.left = deleteNode(root.left, key);
            // delete the current node
        else {
            // the node is a leaf
            if (root.left == null && root.right == null)
                root = null;
                // the node is not a leaf and has a right child
            else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            }
            // the node is not a leaf, has no right child, and has a left child
            else {
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }
}
