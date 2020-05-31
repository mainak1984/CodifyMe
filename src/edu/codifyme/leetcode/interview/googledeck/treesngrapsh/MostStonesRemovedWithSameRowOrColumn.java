package edu.codifyme.leetcode.interview.googledeck.treesngrapsh;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * MEDIUM:
 * https://leetcode.com/explore/interview/card/google/61/trees-and-graphs/3076/
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
}
