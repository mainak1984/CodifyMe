package edu.codifyme.leetcode.interview.googledeck.arraynstring;

import java.util.Arrays;

/**
 * MEDIUM:
 * https://leetcode.com/explore/interview/card/google/59/array-and-strings/3062/
 *
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 * Example 1:
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 *
 * Example 2:
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 *
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 *
 * Approach 1: Use sorting, complexity O(NlogN)
 * Approach 2: Use Heap of size K, complexity O(NlogK)
 * Approach 3: Use quickselect, best case O(N)
 */
public class KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int K) {
        if (K > points.length) {
            return points;
        }
        return kClosest(points, 0, points.length - 1, K - 1);
    }

    public int[][] kClosest(int[][] points, int low, int high, int K) {
        int partition = partition(points, low, high);

        if (partition == K) {
            return Arrays.copyOfRange(points,0,K+1);
        } else {
            if ( partition < K ) {
                return kClosest(points, partition + 1, high, K);
            } else {
                return kClosest(points, low, partition - 1, K);
            }
        }
    }

    public int partition(int[][] points, int low, int high) {
        int pivotloc = low;

        for (int loop = low; loop < high; loop++) {
            if ( value(points, loop) < value(points, high)) {
                swap(points, loop, pivotloc);
                pivotloc++;
            }
        }
        swap(points, high, pivotloc);

        return pivotloc;
    }

    public int value(int[][] points, int index) {
        return (points[index][0]*points[index][0])+(points[index][1]*points[index][1]);
    }

    public void swap(int[][] points, int id1, int id2) {
        int temp0 = points[id1][0];
        int temp1 = points[id1][1];
        points[id1][0] = points[id2][0];
        points[id1][1] = points[id2][1];
        points[id2][0] = temp0;
        points[id2][1] = temp1;
    }
}
