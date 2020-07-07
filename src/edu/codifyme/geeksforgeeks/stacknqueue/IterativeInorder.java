package edu.codifyme.geeksforgeeks.stacknqueue;

import java.util.Stack;

/**
 * MEDIUM: norder Tree Traversal without Recursion
 * https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
 *
 * 1) Create an empty stack S.
 * 2) Initialize current node as root
 * 3) Push the current node to S and set current = current->left until current is NULL
 * 4) If current is NULL and stack is not empty then
 *      a) Pop the top item from stack.
 *      b) Print the popped item, set current = popped_item->right
 *      c) Go to step 3.
 * 5) If current is NULL and stack is empty then we are done.
 *
 */
public class IterativeInorder {
    Node root;
    void inorder()
    {
        if (root == null)
            return;


        Stack<Node> s = new Stack<Node>();
        Node curr = root;

        // traverse the tree
        while (curr != null || s.size() > 0)
        {

            /* Reach the left most Node of the
            curr Node */
            while (curr !=  null)
            {
                /* place pointer to a tree node on
                   the stack before traversing
                  the node's left subtree */
                s.push(curr);
                curr = curr.left;
            }

            /* Current must be NULL at this point */
            curr = s.pop();

            System.out.print(curr.data + " ");

            /* we have visited the node and its
               left subtree.  Now, it's right
               subtree's turn */
            curr = curr.right;
        }
    }

    class Node
    {
        int data;
        Node left, right;

        public Node(int item)
        {
            data = item;
            left = right = null;
        }
    }
}
