package edu.codifyme.leetcode.practice.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 1272. Remove Interval
 * MEDIUM: https://leetcode.com/problems/remove-interval/
 *
 * Given a sorted list of disjoint intervals, each interval intervals[i] = [a, b] represents the set of real numbers x
 * such that a <= x < b.
 *
 * We remove the intersections between any interval in intervals and the interval toBeRemoved.
 * Return a sorted list of intervals after all such removals.
 *
 * Example 1:
 * Input: intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
 * Output: [[0,1],[6,7]]
 *
 * Example 2:
 * Input: intervals = [[0,5]], toBeRemoved = [2,3]
 * Output: [[0,2],[3,5]]
 *
 * Example 3:
 * Input: intervals = [[-5,-4],[-3,-2],[1,2],[3,5],[8,9]], toBeRemoved = [-1,4]
 * Output: [[-5,-4],[-3,-2],[4,5],[8,9]]
 *
 * Constraints:
 * 1 <= intervals.length <= 10^4
 * -10^9 <= intervals[i][0] < intervals[i][1] <= 10^9
 *
 * Approach:
 * Sweep Line algorithm is a sort of geometrical visualisation. Let's imagine a vertical line which is swept across the
 * plane, stopping at some points. That could create various situations, and the decision to make depends on the stop
 * point.
 *
 * Algorithm
 * Let's sweep the line by iterating over input intervals and consider what it could bring to us.
 * - Current interval has no overlaps with toBeRemoved one. That means there is nothing to take care about, just update
 * the output.
 * - Second situation is when toBeRemoved interval is inside of the current interval. Then one has to add two
 * non-overlapping parts of the current interval in the output.
 * - "Left" overlap.
 * - "Right" overlap
 *
 * And here we are, all situations are covered, the job is done.
 */
public class RemoveInterval {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> result = new ArrayList<>();

        for (int[] interval: intervals) {
            if (interval[1] <= toBeRemoved[0] || interval[0] >= toBeRemoved[1]) {
                addToRes(result, interval[0], interval[1]);
                continue;
            }

            if (toBeRemoved[0] > interval[0] && toBeRemoved[0] < interval[1]) {
                addToRes(result, interval[0], toBeRemoved[0]);
            }

            if (toBeRemoved[1] > interval[0] && toBeRemoved[1] < interval[1]) {
                addToRes(result, toBeRemoved[1], interval[1]);
            }
        }

        return result;
    }

    protected void addToRes(List<List<Integer>> res, int start, int end) {
        List<Integer> temp = new ArrayList<>();
        temp.add(start);
        temp.add(end);
        res.add(temp);
    }
}
