package edu.codifyme.geeksforgeeks.treengraph;

/**
 * MEDIUM: Merge two BSTs with limited extra space
 * https://www.geeksforgeeks.org/merge-two-bsts-with-limited-extra-space/
 *
 * Given two Binary Search Trees(BST), print the elements of both BSTs in sorted form. The expected time complexity is O(m+n) where m is the number of nodes in first tree and n is the number of nodes in second tree. Maximum allowed auxiliary space is O(height of the first tree + height of the second tree).
 *
 * Examples:
 *
 * First BST
 *        3
 *     /     \
 *    1       5
 * Second BST
 *     4
 *   /   \
 * 2       6
 * Output: 1 2 3 4 5 6
 *
 *
 * First BST
 *           8
 *          / \
 *         2   10
 *        /
 *       1
 * Second BST
 *           5
 *          /
 *         3
 *        /
 *       0
 * Output: 0 1 2 3 5 8 10
 * Source: Google interview question
 *
 * Solution:
 * The idea is to use iterative inorder traversal. We use two auxiliary stacks for two BSTs. Since we need to print the
 * elements in sorted form, whenever we get a smaller element from any of the trees, we print it. If the element is
 * greater, then we push it back to stack for the next iteration.
 */
public class MergeTwoBST {
    /* A utility function to print
    Inoder traversal of a Binary Tree */
    static void inorder(Node root)
    {
        if (root != null)
        {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    // The function to print data of two BSTs in sorted order
    static void merge(Node root1, Node root2)
    {
        // s1 is stack to hold nodes of first BST
        SNode s1 = new SNode();

        // Current node of first BST
        Node current1 = root1;

        // s2 is stack to hold nodes of second BST
        SNode s2 = new SNode();

        // Current node of second BST
        Node current2 = root2;

        // If first BST is empty, then output is inorder
        // traversal of second BST
        if (root1 == null)
        {
            inorder(root2);
            return;
        }

        // If second BST is empty, then output is inorder
        // traversal of first BST
        if (root2 == null)
        {
            inorder(root1);
            return ;
        }

        // Run the loop while there are nodes not yet printed.
        // The nodes may be in stack(explored, but not printed)
        // or may be not yet explored
        while (current1 != null || !s1.isEmpty() ||
                current2 != null || !s2.isEmpty())
        {

            // Following steps follow iterative Inorder Traversal
            if (current1 != null || current2 != null )
            {
                // Reach the leftmost node of both BSTs and push ancestors of
                // leftmost nodes to stack s1 and s2 respectively
                if (current1 != null)
                {

                    s1.push( current1);
                    current1 = current1.left;
                }
                if (current2 != null)
                {
                    s2.push( current2);
                    current2 = current2.left;
                }

            }
            else
            {

                // If we reach a NULL node and either of the stacks is empty,
                // then one tree is exhausted, ptint the other tree
                if (s1.isEmpty())
                {
                    while (!s2.isEmpty())
                    {
                        current2 = s2.pop ();
                        current2.left = null;
                        inorder(current2);
                    }
                    return ;
                }
                if (s2.isEmpty())
                {
                    while (!s1.isEmpty())
                    {
                        current1 = s1.pop ();
                        current1.left = null;
                        inorder(current1);
                    }
                    return ;
                }

                // Pop an element from both stacks and compare the
                // popped elements
                current1 = s1.pop();

                current2 = s2.pop();

                // If element of first tree is smaller, then print it
                // and push the right subtree. If the element is larger,
                // then we push it back to the corresponding stack.
                if (current1.data < current2.data)
                {
                    System.out.print(current1.data + " ");
                    current1 = current1.right;
                    s2.push( current2);
                    current2 = null;
                }
                else
                {
                    System.out.print(current2.data + " ");
                    current2 = current2.right;
                    s1.push( current1);
                    current1 = null;
                }
            }
        }
        System.out.println(s1.t);
        System.out.println(s2.t);
    }

    // Structure of a BST Node
    class Node
    {

        int data;
        Node left;
        Node right;
        public Node(int data)
        {
            // TODO Auto-generated constructor stub
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // A stack node
    static class SNode
    {
        SNode head;
        Node t;
        SNode next;

        // Function to add an elemt k to stack
        void push(Node k)
        {
            SNode tmp = new SNode();

            // Perform memory check here
            tmp.t = k;
            tmp.next = this.head;
            this.head = tmp;
        }

        // Function to pop an element t from stack
        Node pop()
        {

            SNode st;
            st = this.head;
            head = head.next;

            return st.t;
        }

        // Fucntion to check whether the stack is empty or not
        boolean isEmpty( )
        {
            if (this.head == null )
                return true;

            return false;
        }
    }
}
