package edu.codifyme.leetcode.practice.array;

import java.util.List;

/**
 * 624. Maximum Distance in Arrays
 * MEDIUM: https://leetcode.com/problems/maximum-distance-in-arrays/
 *
 * You are given m arrays, where each array is sorted in ascending order. Now you can pick up two integers from two
 * different arrays (each array picks one) and calculate the distance. We define the distance between two integers
 * a and b to be their absolute difference |a - b|. Your task is to find the maximum distance.
 *
 * Example 1:
 * Input: arrays = [[1,2,3],[4,5],[1,2,3]]
 * Output: 4
 * Explanation: One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the
 * second array.
 *
 * Example 2:
 * Input: arrays = [[1],[1]]
 * Output: 0
 *
 * Example 3:
 * Input: arrays = [[1],[2]]
 * Output: 1
 *
 * Example 4:
 * Input: arrays = [[1,4],[0,5]]
 * Output: 4
 *
 * Constraints:
 * m == arrays.length
 * 2 <= m <= 104
 * 1 <= arrays[i].length <= 500
 * -104 <= arrays[i][j] <= 104
 * arrays[i] is sorted in ascending order.
 * There will be at most 105 integers in all the arrays.
 *
 * Approach:
 * the two points being considered for the distance calculation should not both belong to the same array. Thus, for
 * arrays aa and b currently chosen, we can just find the maximum out of a[n−1]−b[0] and b[m−1]−a[0] to find the larger
 * distance. Here, nn and mm refer to the lengths of arrays aa and bb respectively.
 * But, we need not compare all the array pairs possible to find the maximum distance. Instead, we can keep on traversing
 * over the arrays in the list and keep a track of the maximum distance found so far.
 * To do so, we keep a track of the element with minimum value(min_val) and the one with maximum value(max_val) found so
 * far. Thus, now these extreme values can be treated as if they represent the extreme points of a cumulative array of
 * all the arrays that have been considered till now.
 * For every new array, aa considered, we find the distance a[n−1]−min_val and max_val−a[0] to compete with the maximum
 * distance found so far. Here, nn refers to the number of elements in the current array, aa. Further, we need to note
 * that the maximum distance found till now needs not always be contributed by the end points of the distance being
 * max_val and min_val.
 * But, such points could help in maximizing the distance in the future. Thus, we need to keep track of these maximum
 * and minimum values along with the maximum distance found so far for future calculations. But, in general, the final
 * maximum distance found will always be determined by one of these extreme values, max_val and min_val, or in some
 * cases, by both of them.
 */
public class MaximumDistanceInArrays {
    public int maxDistance(List<List<Integer>> arrays) {
        int maxDiff = 0;
        List<Integer> firstElem = arrays.get(0);
        int low = firstElem.get(0);
        int high = firstElem.get(firstElem.size() - 1);

        for (int i = 1; i < arrays.size(); i++) {
            List<Integer> currElem = arrays.get(i);
            int currLow = currElem.get(0);
            int currHigh = currElem.get(currElem.size() - 1);

            maxDiff = Math.max(maxDiff, Math.max(Math.abs(currHigh - low), Math.abs(high - currLow)));

            low = low < currLow? low: currLow;
            high = high > currHigh? high: currHigh;
        }

        return maxDiff;
    }
}
