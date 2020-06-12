package edu.codifyme.leetcode.interview.googledeck.ztopfifty;

import java.util.*;

/**
 * MEDIUM:
 * https://leetcode.com/problems/campus-bikes/
 *
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D
 * coordinate on this grid.
 * Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike)
 * pair with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are
 * multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker
 * index; if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat this
 * process until there are no available workers.
 *
 * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
 *
 * Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is
 * assigned to.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
 * Output: [1,0]
 * Explanation:
 * Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].
 * Example 2:
 *
 *
 *
 * Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
 * Output: [0,2,1]
 * Explanation:
 * Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned
 * to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].
 *
 *
 * Note:
 * 0 <= workers[i][j], bikes[i][j] < 1000
 * All worker and bike locations are distinct.
 * 1 <= workers.length <= bikes.length <= 1000
 *
 * Approach:
 * Use the |x1 - x2| + |y1 - y2| as the distance and put all pairs of bikes and worker to a priority queue. Pick up
 * each of the point and start assigning worker to bike. Done when all bikes are assigned.
 *
 * Approach 2:
 * Use bucket sort as max distance can be 2000. Create distance between all the worker to bikes and put them in a
 * bucket. Pickup all the bucket and process each item (distance) sequentially. Done when bikes are assigned.
 */
public class CampusBikes {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        Queue<int[]> q = new PriorityQueue<int[]>((a, b)->(a[0] == b[0] ? (a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]) : a[0] - b[0]));
        int i = 0;
        for (int[] worker : workers) {
            int j = 0;
            for (int[] bike : bikes) {
                q.add(new int[]{Math.abs(bike[0] - worker[0]) + Math.abs(bike[1] - worker[1]), i, j++});
            }
            i++;
        }
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Set<Integer> visited = new HashSet<>();
        while (visited.size() < n) {
            int[] temp = q.poll();
            if (res[temp[1]] == -1 && !visited.contains(temp[2])) {
                res[temp[1]] = temp[2];
                visited.add(temp[2]);
            }
        }
        return res;
    }

    // Approach 2: bucket sort
//    public int[] assignBikes(int[][] workers, int[][] bikes) {
//        // Notice that the Manhattan distance is between 0 and 2000,
//        // which means we can sort easily without even using priority queue
//        int w = workers.length, b = bikes.length;
//        int[] wo = new int[w], bi = new int[b];
//        List<int[]>[] dists = new List[2001];
//        Arrays.fill(wo, -1);
//        Arrays.fill(bi, -1);
//        for (int i = 0; i < w; i++) {
//            for (int j = 0; j < b; j++) {
//                int[] worker = workers[i];
//                int[] bike = bikes[j];
//                int dist = Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
//                if (dists[dist] == null) {
//                    dists[dist] = new ArrayList<int[]>();
//                }
//                dists[dist].add(new int[]{i, j});
//            }
//        }
//        int assigned = 0;
//        for (int i = 0; i <= 2000 && assigned < w; i++) {
//            if (dists[i] == null) continue;
//            for (int[] pair : dists[i]) {
//                if (wo[pair[0]] == -1 && bi[pair[1]] == -1) {
//                    wo[pair[0]] = pair[1];
//                    bi[pair[1]] = pair[0];
//                    assigned++;
//                }
//            }
//        }
//        return wo;
//    }
}
