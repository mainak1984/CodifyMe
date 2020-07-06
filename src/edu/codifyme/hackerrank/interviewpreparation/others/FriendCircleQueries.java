package edu.codifyme.hackerrank.interviewpreparation.others;

import java.util.HashMap;
import java.util.Map;

/**
 * MEDIUM:
 * https://www.hackerrank.com/challenges/friend-circle-queries/problem
 *
 * The population of HackerWorld is 10^9. Initially, none of the people are friends with each other. In order to start a
 * friendship, two persons a and b have to shake hands, where 1 <= a,b <= 10^9. The friendship relation is transitive,
 * that is if a and b shake hands with each other, a and friends of a become friends with b and friends of b.
 *
 * You will be given q queries. After each query, you need to report the size of the largest friend circle (the
 * largest group of friends) formed after considering that query.
 *
 * For example, your list of queries is:
 *
 * 1 2
 * 3 4
 * 2 3
 * First, 1 and 2 shake hands, forming a circle of 2. Next, 3 and 4 do the same. Now there are two groups of 2 friends.
 * When 2 and 3 become friends in the next query, both groups of friends are added together to make a circle of 4
 * friends. We would print
 *
 * 2
 * 2
 * 4
 *
 * Function Description:
 * Complete the function maxCircle in the editor below. It must return an array of integers representing the size of the
 * maximum circle of friends after each query.
 *
 * maxCircle has the following parameter(s):
 * * queries: an array of integer arrays, each with two elements indicating a new friendship
 *
 * Approach:
 * Use Union find with path compression to merge new points in wxisting set
 */
public class FriendCircleQueries {
    static class UnionFind {
        Map<Integer, Integer> parents;
        Map<Integer, Integer> sizes;
        int max;
        public UnionFind() {
            parents = new HashMap<>();
            sizes = new HashMap<>();
            max = 0;
        }

        public void union(int v1, int v2) {
            if (!parents.containsKey(v1)) {
                parents.put(v1, v1);
                sizes.put(v1, 1);
            }

            if (!parents.containsKey(v2)) {
                parents.put(v2, v2);
                sizes.put(v2, 1);
            }

            int p1 = find(v1), p2 = find(v2);
            if (p1 == p2) return;
            int s1 = sizes.get(p1), s2 = sizes.get(p2);
            if (s1 < s2) {
                parents.put(p1, p2);
                sizes.put(p2, s1 + s2);
                if (s1 + s2 > max) max = s1 + s2;
            }else {
                parents.put(p2, p1);
                sizes.put(p1, s1 + s2);
                if (s1 + s2 > max) max = s1 + s2;
            }
        }

        public int find(int v) {
            while (parents.get(v) != v) {
                parents.put(v, parents.get(parents.get(v)));
                v = parents.get(v);
            }
            return v;
        }
    }

    // Complete the maxCircle function below.
    static int[] maxCircle(int[][] queries) {
        UnionFind uf = new UnionFind();
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            uf.union(queries[i][0], queries[i][1]);
            res[i] = uf.max;
        }
        return res;
    }
}
