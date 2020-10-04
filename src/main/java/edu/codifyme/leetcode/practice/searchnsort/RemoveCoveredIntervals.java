package edu.codifyme.leetcode.practice.searchnsort;

import java.util.Arrays;

/**
 * 1288. Remove Covered Intervals
 * MEDIUM: https://leetcode.com/problems/remove-covered-intervals/
 *
 * Given a list of intervals, remove all intervals that are covered by another interval in the list.
 * Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.
 * After doing so, return the number of remaining intervals.
 *
 * Example 1:
 * Input: intervals = [[1,4],[3,6],[2,8]]
 * Output: 2
 * Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
 *
 * Example 2:
 * Input: intervals = [[1,4],[2,3]]
 * Output: 1
 *
 * Example 3:
 * Input: intervals = [[0,10],[5,12]]
 * Output: 2
 *
 * Example 4:
 * Input: intervals = [[3,10],[4,10],[5,11]]
 * Output: 2
 *
 * Example 5:
 * Input: intervals = [[1,2],[1,4],[3,4]]
 * Output: 1
 *
 * Constraints:
 * 1 <= intervals.length <= 1000
 * intervals[i].length == 2
 * 0 <= intervals[i][0] < intervals[i][1] <= 10^5
 * All the intervals are unique.
 *
 * Approach:
 * Algorithm
 * - Sort in the ascending order by the start point. If two intervals share the same start point, put the longer one
 *   to be the first.
 * - Initiate the number of non-covered intervals: count = 0.
 * - Iterate over sorted intervals and compare end points.
 *      - If the current interval is not covered by the previous one end > prev_end, increase the number of non-covered
 *        intervals. Assign the current interval to be previous for the next step.
 *      - Otherwise, the current interval is covered by the previous one. Do nothing.
 * - Return count.
 */
public class RemoveCoveredIntervals {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> i1[0] == i2[0]? i1[1] - i2[1]: i1[0] - i2[0]);
        int count = 0;
        int[] prev = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];

            if (curr[1] > prev[1]) {
                if (curr[0] != prev[0]) {
                    count++;
                }
                prev = curr;
            }
        }

        return ++count;
    }

//     public int removeCoveredIntervals(int[][] intervals) {
//         Arrays.sort(intervals, new Comparator<int[]>() {
//           @Override
//           public int compare(int[] o1, int[] o2) {
//             // Sort by start point.
//             // If two intervals share the same start point,
//             // put the longer one to be the first.
//             return o1[0] == o2[0] ? o2[1] - o1[1]: o1[0] - o2[0];
//           }
//         });

//         int count = 0;
//         int end, prev_end = 0;
//         for (int[] curr : intervals) {
//           end = curr[1];
//           // if current interval is not covered
//           // by the previous one
//           if (prev_end < end) {
//             ++count;
//             prev_end = end;
//           }
//         }
//         return count;
//       }
}
