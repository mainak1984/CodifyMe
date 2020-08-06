package edu.codifyme.leetcode.interview.google.others;

import java.util.HashSet;
import java.util.Set;

/**
 * MEDIUM:
 * https://leetcode.com/problems/minimum-area-rectangle
 *
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides
 * parallel to the x and y axes. If there isn't any rectangle, return 0.
 *
 * Example 1:
 * Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
 * Output: 4
 *
 * Example 2:
 * Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
 * Output: 2
 *
 * Note:
 * 1 <= points.length <= 500
 * 0 <= points[i][0] <= 40000
 * 0 <= points[i][1] <= 40000
 * All points are distinct.
 *
 * Approach 2: Count by Diagonal
 * Intuition
 *
 * For each pair of points in the array, consider them to be the long diagonal of a potential rectangle. We can check if
 * all 4 points are there using a Set.
 * For example, if the points are (1, 1) and (5, 5), we check if we also have (1, 5) and (5, 1). If we do, we have a
 * candidate rectangle.
 *
 * Algorithm
 * Put all the points in a set. For each pair of points, if the associated rectangle are 4 distinct points all in the
 * set, then take the area of this rectangle as a candidate answer.
 *
 * Complexity Analysis
 * Time Complexity: O(N^2), where NN is the length of points.
 * Space Complexity: O(N), where HH is the height of the tree.
 */
public class MinimumAreaRectangle {
    public int minAreaRect(int[][] points) {
        Set<Integer> pointSet = new HashSet();
        for (int[] point: points)
            pointSet.add(40001 * point[0] + point[1]);

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; ++i)
            for (int j = i+1; j < points.length; ++j) {
                if (points[i][0] != points[j][0] && points[i][1] != points[j][1]) {
                    if (pointSet.contains(40001 * points[i][0] + points[j][1]) &&
                            pointSet.contains(40001 * points[j][0] + points[i][1])) {
                        ans = Math.min(ans, Math.abs(points[j][0] - points[i][0]) *
                                Math.abs(points[j][1] - points[i][1]));
                    }
                }
            }

        return ans < Integer.MAX_VALUE ? ans : 0;
    }
}
