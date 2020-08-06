package edu.codifyme.leetcode.interview.google.treesngrapsh;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * MEDIUM:
 * https://leetcode.com/problems/most-stones-removed-with-same-row-or-column
 *
 * On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.
 * Now, a move consists of removing a stone that shares a column or row with another stone on the grid.
 * What is the largest possible number of moves we can make?
 *
 * Example 1:
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 *
 * Example 2:
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 *
 * Example 3:
 * Input: stones = [[0,0]]
 * Output: 0
 *
 * Approach: 1
 * Count number of island. The main insight is that we can always make moves that reduce the number of stones in each
 * component to 1. So, total connection possible = stoneNum - islandNum
 *
 * Approach: 2
 * As in Approach 1, we will need to consider components of an underlying graph. A "Disjoint Set Union" (DSU) data
 * structure is ideal for this.
 *
 */
public class MostStonesRemovedWithSameRowOrColumn {
    public int removeStones(int[][] stones) {
        Map<Integer, Set<Integer>> rSet = new HashMap<>(), cSet = new HashMap<>();
        for(int[] e : stones) {
            int r = e[0], c = e[1];
            rSet.putIfAbsent(r, new HashSet<>());
            cSet.putIfAbsent(c, new HashSet<>());
            rSet.get(r).add(c);
            cSet.get(c).add(r);
        }

        int count = 0;
        Set<String> v = new HashSet<>();
        for(int[] e : stones) {
            String key = e[0]+","+e[1];
            if(!v.contains(key)) {
                count++;
                v.add(key);
                dfs(e[0], e[1], rSet, cSet, v);
            }
        }
        return stones.length - count;
    }

    void dfs(int r, int c, Map<Integer, Set<Integer>> rSet, Map<Integer, Set<Integer>> cSet, Set<String> v) {
        for(int y : rSet.get(r)) {
            String key = r+","+y;
            if(!v.contains(key)) {
                v.add(key);
                dfs(r, y, rSet, cSet, v);
            }
        }

        for(int x : cSet.get(c)) {
            String key = x+","+c;
            if(!v.contains(key)) {
                v.add(key);
                dfs(x, c, rSet, cSet, v);
            }
        }
    }

    // APPROACH: 2: Union Find
//    public int removeStones(int[][] stones) {
//        int N = stones.length;
//        DSU dsu = new DSU(20000);
//
//        for (int[] stone: stones)
//            dsu.union(stone[0], stone[1] + 10000);
//
//        Set<Integer> seen = new HashSet();
//        for (int[] stone: stones)
//            seen.add(dsu.find(stone[0]));
//
//        return N - seen.size();
//    }
//}
//
//class DSU {
//    int[] parent;
//    public DSU(int N) {
//        parent = new int[N];
//        for (int i = 0; i < N; ++i)
//            parent[i] = i;
//    }
//    public int find(int x) {
//        if (parent[x] != x) parent[x] = find(parent[x]);
//        return parent[x];
//    }
//    public void union(int x, int y) {
//        parent[find(x)] = find(y);
//    }
//}
}
