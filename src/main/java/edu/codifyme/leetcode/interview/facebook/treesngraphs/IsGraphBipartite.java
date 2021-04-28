package edu.codifyme.leetcode.interview.facebook.treesngraphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 785. Is Graph Bipartite?
 * MEDIUM: https://leetcode.com/problems/is-graph-bipartite/
 *
 * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array
 * graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there
 * is an undirected edge between node u and node v. The graph has the following properties:
 *
 * There are no self-edges (graph[u] does not contain u).
 * There are no parallel edges (graph[u] does not contain duplicate values).
 * If v is in graph[u], then u is in graph[v] (the graph is undirected).
 * The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
 * A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the
 * graph connects a node in set A and a node in set B.
 *
 * Return true if and only if it is bipartite.
 *
 * Example 1:
 * Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 * Output: false
 * Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node
 * in one and a node in the other.
 *
 * Example 2:
 * Input: graph = [[1,3],[0,2],[1,3],[0,2]]
 * Output: true
 * Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.
 *
 * Constraints:
 * graph.length == n
 * 1 <= n <= 100
 * 0 <= graph[u].length < n
 * 0 <= graph[u][i] <= n - 1
 * graph[u] does not contain u.
 * All the values of graph[u] are unique.
 * If graph[u] contains v, then graph[v] contains u.
 *
 * Approach:
 * Use a two coloring approach.
 * If DFS is done, if neighbouring nodes are unvisited, color in reverse color else check if neighbor is other color
 * If BFS is done, check alternate color is present at different levels
 */
public class IsGraphBipartite {
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> result = new LinkedList<>();

        if (root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            Queue<TreeNode> temp = new LinkedList<>();
            TreeNode item = null;

            while (!queue.isEmpty()) {
                item = queue.poll();
                if (item.left != null) {
                    temp.add(item.left);
                }

                if (item.right != null) {
                    temp.add(item.right);
                }
            }

            result.add(item.val);
            queue = temp;
        }

        return result;
    }

    private class TreeNode {
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
