package edu.codifyme.leetcode.interview.googledeck.sortnsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. Merge Intervals
 * MEDIUM: https://leetcode.com/problems/merge-intervals/
 *
 * Included in Mock set
 *
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 *
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method
 * signature.
 *
 * SOLUTION: Sort intervals by starting and look for overlap
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        int[][] items = null;

        if (intervals.length == 0) {
            return new int[0][2];
        }

        List<Integer> startList = new ArrayList<>();
        List<Integer> endList = new ArrayList<>();

        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

        int currentStart = intervals[0][0];
        int currentEnd = intervals[0][1];

        for ( int j = 1; j < intervals.length; j++ ) {
            if ( intervals[j][0] <= currentEnd ) {
                currentEnd = (currentEnd < intervals[j][1])? intervals[j][1] : currentEnd;
                continue;
            }

            startList.add(currentStart);
            endList.add(currentEnd);

            currentStart = intervals[j][0];
            currentEnd = intervals[j][1];
        }

        startList.add(currentStart);
        endList.add(currentEnd);

        items = new int[startList.size()][2];

        for ( int k = 0; k < startList.size(); k++ ) {
            items[k][0] = startList.get(k);
            items[k][1] = endList.get(k);
        }

        return items;
    }
}
