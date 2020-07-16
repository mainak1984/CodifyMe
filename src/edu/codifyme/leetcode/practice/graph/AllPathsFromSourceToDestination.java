package edu.codifyme.leetcode.practice.graph;

import java.util.*;

/**
 * MEDIUM: All Paths from Source Lead to Destination
 * https://leetcode.com/problems/all-paths-from-source-lead-to-destination/
 *
 * Given the edges of a directed graph, and two nodes source and destination of this graph, determine whether or not all
 * paths starting from source eventually end at destination, that is:
 *
 * At least one path exists from the source node to the destination node
 * If a path exists from the source node to a node with no outgoing edges, then that node is equal to destination.
 * The number of possible paths from source to destination is a finite number.
 * Return true if and only if all roads from source lead to destination.
 *
 * Example 1:
 * Input: n = 3, edges = [[0,1],[0,2]], source = 0, destination = 2
 * Output: false
 * Explanation: It is possible to reach and get stuck on both node 1 and node 2.
 *
 * Example 2:
 * Input: n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
 * Output: false
 * Explanation: We have two possibilities: to end at node 3, or to loop over node 1 and node 2 indefinitely.
 *
 * Example 3:
 * Input: n = 4, edges = [[0,1],[0,2],[1,3],[2,3]], source = 0, destination = 3
 * Output: true
 *
 * Example 4:
 * Input: n = 3, edges = [[0,1],[1,1],[1,2]], source = 0, destination = 2
 * Output: false
 * Explanation: All paths from the source node end at the destination node, but there are an infinite number of paths,
 * such as 0-1-2, 0-1-1-2, 0-1-1-1-2, 0-1-1-1-1-2, and so on.
 *
 * Example 5:
 * Input: n = 2, edges = [[0,1],[1,1]], source = 0, destination = 1
 * Output: false
 * Explanation: There is infinite self-loop at destination node.
 *
 * Note:
 * The given graph may have self loops and parallel edges.
 * The number of nodes n in the graph is between 1 and 10000
 * The number of edges in the graph is between 0 and 10000
 * 0 <= edges.length <= 10000
 * edges[i].length == 2
 * 0 <= source <= n - 1
 * 0 <= destination <= n - 1
 *
 * Approach:
 * DFS and verify if we return from a node which is not destination
 * mark all nodes as visited while traversing, add nodes after visiting in accepted list
 * if anytime meet visited node which is not in accepted list -> means loop
 * any time destination node having further child means false
 */
public class AllPathsFromSourceToDestination {
    Map<Integer, Set<Integer>> graph;

    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> accepted = new HashSet<>();

        // if no self loop on destination
        if ( false == buildGraph(n, edges, destination)) {
            return false;
        }

        return dfs(visited, accepted, source, destination);
    }

    boolean dfs(Set<Integer> visited, Set<Integer> accepted, int node, int destination) {
        boolean allChildVisited = true;

        if (node == destination) {
            if (graph.containsKey(node)) {
                return false;
            } else {
                return true;
            }
        }

        // mark visited
        visited.add(node);

        // if it has no child, return false
        if (!graph.containsKey(node)) {
            return false;
        }

        // for all child, go dfs for the child
        // if child is visited, but not in accepted set, return false
        for (Integer child: graph.get(node)) {
            if ( !visited.contains(child) ) {
                allChildVisited = false;
                if (dfs(visited, accepted, child, destination) == false) {
                    return false;
                }
            } else if ( !accepted.contains(child) ) {
                return false;
            }
        }

        accepted.add(node);
        return !allChildVisited;
    }

    boolean buildGraph(int vertex, int[][] edges, int destination) {
        graph = new HashMap<>(vertex);

        for ( int i = 0; i < edges.length; i++ ) {
            if (edges[i][0] == destination && edges[i][1] == destination) {
                return false;
            }

            Set<Integer> innerSet = graph.get(edges[i][0]);
            if (innerSet == null) {
                innerSet = new HashSet<Integer>();
                graph.put(edges[i][0], innerSet);
            }
            innerSet.add(edges[i][1]);
        }

        return true;
    }

    // Efficient Implementation
//    List<Integer>[] adjList;
//    BitSet visited;
//
//    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
//
//        visited = new BitSet(n);
//        adjList  = new ArrayList[n];
//
//        for (int i = 0; i < n; i++) {
//
//            adjList[i] = new ArrayList<>();
//        }
//
//        for (int i = 0; i < edges.length; i++) {
//            if(edges[i][0] == destination) return false;
//            adjList[edges[i][0]].add(edges[i][1]);
//        }
//
//
//        return dfs(source, destination);
//    }
//
//    private boolean dfs(int source, int destination) {
//        if(adjList[source].size() == 0){
//            return source == destination;
//        }
//
//        visited.set(source);
//
//        for(int next : adjList[source]){
//            if(visited.get(next)) return false;
//
//            if(!dfs(next, destination)) return false;
//        }
//
//        visited.set(source,false);
//        return true;
//    }
}
