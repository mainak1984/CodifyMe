package edu.codifyme.leetcode.practice.stacknqueue;

import java.util.*;

/**
 * 1345. Jump Game IV
 * HARD: https://leetcode.com/problems/jump-game-iv/
 *
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 * In one step you can jump from index i to index:
 *
 * i + 1 where: i + 1 < arr.length.
 * i - 1 where: i - 1 >= 0.
 * j where: arr[i] == arr[j] and i != j.
 * Return the minimum number of steps to reach the last index of the array.
 *
 * Notice that you can not jump outside of the array at any time.
 *
 * Example 1:
 * Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
 * Output: 3
 * Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
 *
 * Example 2:
 * Input: arr = [7]
 * Output: 0
 * Explanation: Start index is the last index. You don't need to jump.
 *
 * Example 3:
 * Input: arr = [7,6,9,6,9,6,9,7]
 * Output: 1
 * Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 *
 * Example 4:
 * Input: arr = [6,1,9]
 * Output: 2
 *
 * Example 5:
 * Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
 * Output: 3
 *
 *
 * Constraints:
 * 1 <= arr.length <= 5 * 10^4
 * -10^8 <= arr[i] <= 10^8
 *
 * Approach:
 * BFS: From convenience, we can store nodes with the same value together in a graph dictionary. With this method, when
 * searching, we do not need to iterate the whole list to find the nodes with the same value as the next steps, but only
 * need to ask the precomputed dictionary. However, to prevent stepping back, we need to clear the dictionary after we
 * get to that value.
 *
 * Bidirectional BFS:
 * In the later part of our original BFS method, the layer may be long and takes a long time to compute the next layer.
 * In this situation, we can compute the layer from the end, which may be short and takes less time.
 */
public class JumpGameIV {
    // Approach 2: Bidirectional BFS
    public static int minJumps(int[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return 0;
        }

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.computeIfAbsent(arr[i], v -> new LinkedList<>()).add(i);
        }

        List<Integer> curs = new LinkedList<>(); // store layers from start
        curs.add(0);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        visited.add(n - 1);
        int step = 0;

        List<Integer> other = new LinkedList<>(); // store layers from end
        other.add(n - 1);

        // when current layer exists
        while (!curs.isEmpty()) {
            // search from the side with fewer nodes
            if (curs.size() > other.size()) {
                List<Integer> tmp = curs;
                curs = other;
                other = tmp;
            }

            List<Integer> nex = new LinkedList<>();

            // iterate the layer
            for (int node : curs) {

                // check same value
                for (int child : graph.get(arr[node])) {
                    if (other.contains(child)) {
                        return step + 1;
                    }
                    if (!visited.contains(child)) {
                        visited.add(child);
                        nex.add(child);
                    }
                }

                // clear the list to prevent redundant search
                graph.get(arr[node]).clear();

                // check neighbors
                if (other.contains(node + 1) || other.contains(node - 1)) {
                    return step + 1;
                }

                if (node + 1 < n && !visited.contains(node + 1)) {
                    visited.add(node + 1);
                    nex.add(node + 1);
                }
                if (node - 1 >= 0 && !visited.contains(node - 1)) {
                    visited.add(node - 1);
                    nex.add(node - 1);
                }
            }

            curs = nex;
            step++;
        }

        return -1;
    }
}
