package edu.codifyme.geeksforgeeks.treengraph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * EASY: Connect nodes at same level
 * https://www.geeksforgeeks.org/connect-nodes-at-same-level/
 *
 * Write a function to connect all the adjacent nodes at the same level in a binary tree. Structure of the given Binary
 * Tree node is like following.
 * Initially, all the nextRight pointers point to garbage values. Your function should set these pointers to point next
 * right for each node
 *
 * Example:
 *
 * Input Tree
 *        A
 *       / \
 *      B   C
 *     / \   \
 *    D   E   F
 *
 * Output Tree
 *        A--->NULL
 *       / \
 *      B-->C-->NULL
 *     / \   \
 *    D-->E-->F-->NULL
 *
 * Approach:
 * Use BFS for performing level order traversal and add link
 */
public class ConnectNodeAtSameLevel {
    Node root;
    void connect(Node p)
    {
        // initialize queue to hold nodes at same level
        Queue<Node> q = new LinkedList<>();

        q.add(root); // adding nodes to tehe queue

        Node temp = null; // initializing prev to null
        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                Node prev = temp;
                temp = q.poll();

                // i > 0 because when i is 0 prev points
                // the last node of previous level,
                // so we skip it
                if (i > 0)
                    prev.nextRight = temp;

                if (temp.left != null)
                    q.add(temp.left);

                if (temp.right != null)
                    q.add(temp.right);
            }

            // pointing last node of the nth level to null
            temp.nextRight = null;
        }
    }

    class Node {
        int data;
        Node left, right, nextRight;

        Node(int item)
        {
            data = item;
            left = right = nextRight = null;
        }
    }
}
