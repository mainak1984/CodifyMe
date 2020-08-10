package edu.codifyme.leetcode.mocktest.google.graph;

import java.util.*;

/**
 * 743. Network Delay Time
 * MEDIUM: https://leetcode.com/problems/network-delay-time/
 *
 * There are N network nodes, labelled 1 to N.
 *
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the
 * target node, and w is the time it takes for a signal to travel from source to target.
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is
 * impossible, return -1.
 *
 * Example 1:
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
 * Output: 2
 *
 * Note:
 * N will be in the range [1, 100].
 * K will be in the range [1, N].
 * The length of times will be in the range [1, 6000].
 * All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
 *
 * Approach 1: DFS
 * Let's record the time dist[node] when the signal reaches the node. If some signal arrived earlier, we don't need to broadcast it anymore. Otherwise, we should broadcast the signal.
 *
 * Algorithm
 * We'll maintain dist[node], the earliest that we arrived at each node. When visiting a node while elapsed time has
 * elapsed, if this is the currently-fastest signal at this node, let's broadcast signals from this node.
 * To speed things up, at each visited node we'll consider signals exiting the node that are faster first, by sorting
 * the edges.
 *
 * Complexity Analysis
 *
 * Time Complexity: O(N^N + E log E) where EE is the length of times. We can only fully visit each node up to N-1 times,
 * one per each other node. Plus, we have to explore every edge and sort them. Sorting each small bucket of outgoing
 * edges is bounded by sorting all of them, because of repeated use of the inequality xlogx + ylogy ≤(x+y)log(x+y).
 *
 * Space Complexity: O(N + E), the size of the graph O(E), plus the size of the implicit call stack in our DFS O(N).
 *
 * Approach 2: Dijikstra
 * Intuition and Algorithm
 * We use Dijkstra's algorithm to find the shortest path from our source to all targets. This is a textbook algorithm,
 * refer to this link for more details.
 * Dijkstra's algorithm is based on repeatedly making the candidate move that has the least distance travelled.
 */
public class NetworkDelayTime {
    Map<Integer, Integer> dist;
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int[] edge: times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            graph.get(edge[0]).add(new int[]{edge[2], edge[1]});
        }
        for (int node: graph.keySet()) {
            Collections.sort(graph.get(node), (a, b) -> a[0] - b[0]);
        }
        dist = new HashMap();
        for (int node = 1; node <= N; ++node)
            dist.put(node, Integer.MAX_VALUE);

        dfs(graph, K, 0);
        int ans = 0;
        for (int cand: dist.values()) {
            if (cand == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, cand);
        }
        return ans;
    }

    public void dfs(Map<Integer, List<int[]>> graph, int node, int elapsed) {
        if (elapsed >= dist.get(node)) return;
        dist.put(node, elapsed);
        if (graph.containsKey(node))
            for (int[] info: graph.get(node))
                dfs(graph, info[1], elapsed + info[0]);
    }

    // Approach 2: Dijikstra
//    public int networkDelayTime(int[][] times, int N, int K) {
//        Map<Integer, List<int[]>> graph = new HashMap();
//        for (int[] edge: times) {
//            if (!graph.containsKey(edge[0]))
//                graph.put(edge[0], new ArrayList<int[]>());
//            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
//        }
//        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(
//                (info1, info2) -> info1[0] - info2[0]);
//        heap.offer(new int[]{0, K});
//
//        Map<Integer, Integer> dist = new HashMap();
//
//        while (!heap.isEmpty()) {
//            int[] info = heap.poll();
//            int d = info[0], node = info[1];
//            if (dist.containsKey(node)) continue;
//            dist.put(node, d);
//            if (graph.containsKey(node))
//                for (int[] edge: graph.get(node)) {
//                    int nei = edge[0], d2 = edge[1];
//                    if (!dist.containsKey(nei))
//                        heap.offer(new int[]{d+d2, nei});
//                }
//        }
//
//        if (dist.size() != N) return -1;
//        int ans = 0;
//        for (int cand: dist.values())
//            ans = Math.max(ans, cand);
//        return ans;
//    }

}
