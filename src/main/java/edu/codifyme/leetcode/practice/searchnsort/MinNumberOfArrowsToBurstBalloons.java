package edu.codifyme.leetcode.practice.searchnsort;

import java.util.Arrays;

/**
 * 452. Minimum Number of Arrows to Burst Balloons
 * MEDIUM: https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 *
 * There are some spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and
 * end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter, and hence the
 * x-coordinates of start and end of the diameter suffice. The start is always smaller than the end.
 *
 * An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend
 * bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An
 * arrow once shot keeps traveling up infinitely.
 *
 * Given an array points where points[i] = [xstart, xend], return the minimum number of arrows that must be shot to
 * burst all balloons.
 *
 * Example 1:
 * Input: points = [[10,16],[2,8],[1,6],[7,12]]
 * Output: 2
 * Explanation: One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another
 * arrow at x = 11 (bursting the other two balloons).
 *
 * Example 2:
 * Input: points = [[1,2],[3,4],[5,6],[7,8]]
 * Output: 4
 *
 * Example 3:
 * Input: points = [[1,2],[2,3],[3,4],[4,5]]
 * Output: 2
 *
 * Example 4:
 * Input: points = [[1,2]]
 * Output: 1
 *
 * Example 5:
 * Input: points = [[2,3],[2,3]]
 * Output: 1
 *
 * Constraints:
 * 0 <= points.length <= 104
 * points.length == 2
 * -231 <= xstart < xend <= 231 - 1
 *
 * Approach: Greedy
 * Now the algorithm is straightforward :
 * - Sort the balloons by end coordinate x_end.
 * - Initiate the end coordinate of a balloon which ends first : first_end = points[0][1].
 * - Initiate number of arrows: arrows = 1.
 * - Iterate over all balloons:
 *      - If the balloon starts after first_end:
 *          - Increase the number of arrows by one.
 *          - Set first_end to be equal to the end of the current balloon.
 * - Return arrows.
 */
public class MinNumberOfArrowsToBurstBalloons {
    public int findMinArrowShots(int[][] points) {
        int result = 0;
        if (points.length == 0) {
            return result;
        }

        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));

        int pEnd = points[0][1];

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= pEnd) {
                pEnd = Math.min(pEnd, points[i][1]);
            } else {
                result++;
                pEnd = points[i][1];
            }
        }

        return result+1;
    }
}
