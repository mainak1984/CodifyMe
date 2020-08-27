package edu.codifyme.leetcode.practice.searchnsort;

import java.util.TreeMap;

/**
 * 436. Find Right Interval
 * https://leetcode.com/problems/find-right-interval/
 *
 * Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger
 * than or equal to the end point of the interval i, which can be called that j is on the "right" of i.
 *
 * For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum
 * start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the
 * interval i. Finally, you need output the stored value of each interval as an array.
 *
 * Note:
 * You may assume the interval's end point is always bigger than its start point.
 * You may assume none of these intervals have the same start point.
 *
 *
 * Example 1:
 * Input: [ [1,2] ]
 *
 * Output: [-1]
 *
 * Explanation: There is only one interval in the collection, so it outputs -1.
 *
 *
 * Example 2:
 * Input: [ [3,4], [2,3], [1,2] ]
 *
 * Output: [-1, 0, 1]
 *
 * Explanation: There is no satisfied "right" interval for [3,4].
 * For [2,3], the interval [3,4] has minimum-"right" start point;
 * For [1,2], the interval [2,3] has minimum-"right" start point.
 *
 *
 * Example 3:
 * Input: [ [1,4], [2,3], [3,4] ]
 *
 * Output: [-1, 2, -1]
 *
 * Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
 * For [2,3], the interval [3,4] has minimum-"right" start point.
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method
 * signature.
 *
 * Approach:
 *
 * 1. Use TreeMap to track the greater keys. Complexity O(nlogn)
 *
 * 2. Using Two Arrays without Binary Search
 * The intuition behind this approach is as follows: If we maintain two arrays,
 *
 * \text{intervals}intervals, which is sorted based on the start points.
 *
 * \text{endIntervals}endIntervals, which is sorted based on the end points.
 *
 * Once we pick up the first interval(or, say the ith interval) from the endIntervals array, we can determine the
 * appropriate interval satisfying the right interval criteria by scanning the intervals in intervals array from left
 * towards the right, since the intervals array is sorted based on the start points. Say, the index of the element
 * chosen from the intervals array happens to be j.
 *
 * Now, when we pick up the next interval(say the (i+1)th interval) from the endIntervals array, we need not start
 * scanning the intervals array from the first index. Rather, we can start off directly from the jth index where we left
 * off last time in the intervals array. This is because end point corresponding to endIntervals[i+1] is larger than the
 * one corresponding to endIntervals[i] and none of the intervals from intervals[k], such that 0< k < j, satisfies the
 * right neighbor criteria with endIntervals[i], and hence not with }endIntervals[i+1] as well.
 *
 * If at any moment, we reach the end of the array i.e. j=intervals.length and no element satisfying the right interval
 * criteria is available in the intervals array, we put a -1 in the corresponding res entry. The same holds for all the
 * remaining elements of the endIntervals array, whose end points are even larger than the previous interval encountered.
 *
 * Also we make use of a hashmap hash initially to preserve the indices corresponding to the intervals even after sorting.
 */
public class FindRightInterval {
    public int[] findRightInterval(int[][] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[] result = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            map.put(intervals[i][0], i);
        }

        for (int i = 0; i < intervals.length; i++) {
            Integer key = map.ceilingKey(intervals[i][1]);

            if (null == key) {
                result[i] = -1;
            } else {
                result[i] = map.get(key);
            }
        }

        return result;
    }

    // Approach 2
//    public int[] findRightInterval(int[][] intervals) {
//        int[][] endIntervals = Arrays.copyOf(intervals, intervals.length);
//        HashMap<int[], Integer> hash = new HashMap<>();
//        for (int i = 0; i < intervals.length; i++) {
//            hash.put(intervals[i], i);
//        }
//        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
//        Arrays.sort(endIntervals, (a, b) -> a[1] - b[1]);
//        int j = 0;
//        int[] res = new int[intervals.length];
//        for (int i = 0; i < endIntervals.length; i++) {
//            while (j < intervals.length && intervals[j][0] < endIntervals[i][1]) {
//                j++;
//            }
//            res[hash.get(endIntervals[i])] = j == intervals.length ? -1 : hash.get(intervals[j]);
//        }
//        return res;
//    }
}
