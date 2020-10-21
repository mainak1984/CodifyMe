package edu.codifyme.leetcode.practice.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 133. Clone Graph
 * MEDIUM: https://leetcode.com/problems/clone-graph/
 *
 * Given a reference of a node in a connected undirected graph.
 * Return a deep copy (clone) of the graph.
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 * Test case format:
 * For simplicity sake, each node's value is the same as the node's index (1-indexed). For example, the first node with
 * val = 1, the second node with val = 2, and so on. The graph is represented in the test case using an adjacency list.
 *
 * Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of
 * neighbors of a node in the graph.
 *
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference
 * to the cloned graph.
 *
 * Example 1:
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 *
 * Example 2:
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it
 * does not have any neighbors.
 *
 * Example 3:
 *
 * Input: adjList = []
 * Output: []
 * Explanation: This an empty graph, it does not have any nodes.
 *
 * Example 4:
 * Input: adjList = [[2],[1]]
 * Output: [[2],[1]]
 *
 * Constraints:
 * 1 <= Node.val <= 100
 * Node.val is unique for each node.
 * Number of Nodes will not exceed 100.
 * There is no repeated edges and no self-loops in the graph.
 * The Graph is connected and all nodes can be visited starting from the given node.
 *
 * Approach: DFS / BFS
 * Algorithm :
 * - Start traversing the graph from the given node.
 *
 * - We would take a hash map to store the reference of the copy of all the nodes that have already been visited and
 * cloned. The key for the hash map would be the node of the original graph and corresponding value would be the
 * corresponding cloned node of the cloned graph. If the node already exists in the visited we return corresponding
 * stored reference of the cloned node.
 * For a given edge A - B, since A is connected to B and B is also connected to A if we don't use visited we will get stuck in a cycle.
 *
 * - If we don't find the node in the visited hash map, we create a copy of it and put it in the hash map. Note, how
 * it's important to create a copy of the node and add to the hash map before entering recursion.
 *
 *    clone_node = Node(node.val, [])
 *    visited[node] = clone_node
 *
 * In the absence of such an ordering, we would be caught in the recursion because on encountering the node again in
 * somewhere down the recursion again, we will be traversing it again thus getting into cycles.
 *
 * - Now make the recursive call for the neighbors of the node. Pay attention to how many recursion calls we will be
 * making for any given node. For a given node the number of recursive calls would be equal to the number of its
 * neighbors. Each recursive call made would return the clone of a neighbor. We will prepare the list of these clones
 * returned and put into neighbors of clone node which we had created earlier. This way we will have cloned the given
 * node and it's neighbors.
 */
public class CloneGraph {
    Map<Integer, Node> nodeMap = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (null == node) {
            return null;
        }

        if (nodeMap.containsKey(node.val)) {
            return nodeMap.get(node.val);
        }

        Node result = new Node(node.val);
        nodeMap.put(node.val, result);

        for (Node child: node.neighbors) {
            result.neighbors.add(cloneGraph(child));
        }

        return result;
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
