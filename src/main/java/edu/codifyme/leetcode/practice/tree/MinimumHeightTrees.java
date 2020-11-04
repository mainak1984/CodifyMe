package edu.codifyme.leetcode.practice.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 310. Minimum Height Trees
 * MEDIUM: https://leetcode.com/problems/minimum-height-trees/
 *
 * A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any
 * connected graph without simple cycles is a tree.
 *
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates
 * that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as
 * the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those
 * with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 *
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 *
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 *
 * Example 1:
 * Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 * Output: [1]
 * Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
 *
 * Example 2:
 * Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * Output: [3,4]
 *
 * Example 3:
 * Input: n = 1, edges = []
 * Output: [0]
 *
 * Example 4:
 * Input: n = 2, edges = [[0,1]]
 * Output: [0,1]
 *
 * Constraints:
 * 1 <= n <= 2 * 104
 * edges.length == n - 1
 * 0 <= ai, bi < n
 * ai != bi
 * All the pairs (ai, bi) are distinct.
 * The given input is guaranteed to be a tree and there will be no repeated edges.
 *
 * Approach:
 * Algorithm:
 * Given the above intuition, the problem is now reduced down to looking for all the centroid nodes in a tree-alike
 * graph, which in addition are no more than two.
 * The idea is that we trim out the leaves nodes layer by layer, until we reach the core of the graph, which are the
 * centroids nodes.
 * Once we trim out the first layer of the leaves nodes (nodes that have only one connection), some of the non-leaf
 * nodes would become leaves nodes.
 * The trimming process continues until there are only two nodes left in the graph, which are the centroids that we are
 * looking for.
 * The above algorithm resembles the topological sorting algorithm which generates the order of objects based on their
 * dependencies. For instance, in the scenario of course scheduling, the courses that have the least dependency would
 * appear first in the order.
 * In our case, we trim out the leaf nodes first, which are the farther away from the centroids. At each step, the nodes
 * we trim out are closer to the centroids than the nodes in the previous step. At the end, the trimming process
 * terminates at the centroids nodes.
 *
 * Implementation:
 * Given the above algorithm, we could implement it via the Breadth First Search (BFS) strategy, to trim the leaves
 * nodes layer by layer (i.e. level by level).
 * - Initially, we would build a graph with the adjacency list from the input.
 * - We then create a queue which would be used to hold the leaves nodes.
 * - At the beginning, we put all the current leaves nodes into the queue.
 * - We then run a loop until there is only two nodes left in the graph.
 * - At each iteration, we remove the current leaves nodes from the queue. While removing the nodes, we also remove
 * the edges that are linked to the nodes. As a consequence, some of the non-leaf nodes would become leaves nodes. And
 * these are the nodes that would be trimmed out in the next iteration.
 * - The iteration terminates when there are no more than two nodes left in the graph, which are the desired centroids
 * nodes.
 *
 * Here are some sample implementations that are inspired from the post of dietpepsi in the discussion forum.
 */
public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        // base cases
        if (n < 2) {
            ArrayList<Integer> centroids = new ArrayList<>();
            for (int i = 0; i < n; i++)
                centroids.add(i);
            return centroids;
        }

        // Build the graph with the adjacency list
        ArrayList<Set<Integer>> neighbors = new ArrayList<>();
        for (int i = 0; i < n; i++)
            neighbors.add(new HashSet<Integer>());

        for (int[] edge : edges) {
            Integer start = edge[0], end = edge[1];
            neighbors.get(start).add(end);
            neighbors.get(end).add(start);
        }

        // Initialize the first layer of leaves
        ArrayList<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (neighbors.get(i).size() == 1)
                leaves.add(i);

        // Trim the leaves until reaching the centroids
        int remainingNodes = n;
        while (remainingNodes > 2) {
            remainingNodes -= leaves.size();
            ArrayList<Integer> newLeaves = new ArrayList<>();

            // remove the current leaves along with the edges
            for (Integer leaf : leaves) {
                for (Integer neighbor : neighbors.get(leaf)) {
                    neighbors.get(neighbor).remove(leaf);
                    if (neighbors.get(neighbor).size() == 1)
                        newLeaves.add(neighbor);
                }
            }

            // prepare for the next round
            leaves = newLeaves;
        }

        // The remaining nodes are the centroids of the graph
        return leaves;
    }
}
