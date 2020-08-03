package edu.codifyme.leetcode.interview.mocktest.google.array;

import java.util.ArrayList;
import java.util.List;

/**
 * MEDIUM: Shortest Distance to Target Color
 * https://leetcode.com/problems/shortest-distance-to-target-color/
 *
 * You are given an array colors, in which there are three colors: 1, 2 and 3.
 *
 * You are also given some queries. Each query consists of two integers i and c, return the shortest distance between
 * the given index i and the target color c. If there is no solution return -1.
 *
 * Example 1:
 * Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
 * Output: [3,0,3]
 * Explanation:
 * The nearest 3 from index 1 is at index 4 (3 steps away).
 * The nearest 2 from index 2 is at index 2 itself (0 steps away).
 * The nearest 1 from index 6 is at index 3 (3 steps away).
 *
 * Example 2:
 * Input: colors = [1,2], queries = [[0,3]]
 * Output: [-1]
 * Explanation: There is no 3 in the array.
 *
 * Constraints:
 * 1 <= colors.length <= 5*10^4
 * 1 <= colors[i] <= 3
 * 1 <= queries.length <= 5*10^4
 * queries[i].length == 2
 * 0 <= queries[i][0] < colors.length
 * 1 <= queries[i][1] <= 3
 *
 * Approach:
 * Build Min and Max array from both sides. Use the array to find out the nearest location of character occurrence
 */
public class ShortestDistanceToTargetColor {
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        int[][] minArr = new int[3][colors.length];
        int[][] maxArr = new int[3][colors.length];
        List<Integer> result = new ArrayList<>();

        // build min index
        for (int i = 0; i < 3; i++) {
            int prev = -1;

            for (int j = 0; j < colors.length; j++) {
                if (colors[j] == i + 1) {
                    prev = j;
                }
                minArr[i][j] = prev;
            }
        }

        // build max index
        for (int i = 0; i < 3; i++) {
            int prev = -1;

            for (int j = colors.length - 1; j >= 0; j--) {
                if (colors[j] == i + 1) {
                    prev = j;
                }
                maxArr[i][j] = prev;
            }
        }

        for (int[] query: queries) {
            int minD = minArr[query[1]-1][query[0]];
            int maxD = maxArr[query[1]-1][query[0]];

            int min = Integer.MAX_VALUE;

            if ( minD != -1 ) {
                min = Math.min(min, query[0] - minD);
            }
            if ( maxD != -1 ) {
                min = Math.min(min, maxD - query[0]);
            }

            result.add( min == Integer.MAX_VALUE? -1: min);
        }

        return result;
    }
}
