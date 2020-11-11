package edu.codifyme.leetcode.practice.others;

/**
 * 593. Valid Square
 * MEDIUM: https://leetcode.com/problems/valid-square/
 *
 * Given the coordinates of four points in 2D space, return whether the four points could construct a square.
 * The coordinate (x,y) of a point is represented by an integer array with two integers.
 *
 * Example:
 * Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * Output: True
 *
 * Note:
 * All the input integers are in the range [-10000, 10000].
 * A valid square has four equal sides with positive length and four equal angles (90-degree angles).
 * Input points have no order.
 *
 * Approach: Brute force
 * Firstly, we need to determine if the sides of the quadrilateral formed by these 4 points are equal. But checking only
 * this won't suffice. Since, this condition will be satisfied even in the case of a rhombus, where all the four sides
 * are equal but the adjacent sides aren't perpendicular to each other. Thus, we also need to check if the lengths of
 * the diagonals formed between the corners of the quadrilateral are equal. If both the conditions are satisfied, then
 * only the given set of points can be deemed appropriate for constituting a square.
 *
 * Approach: Sorting
 * Instead of considering all the permutations of arrangements possible, we can make use of maths to simplify this
 * problem a bit. If we sort the given set of points based on their x-coordinate values, and in the case of a tie, based
 * on their y-coordinate value, we can obtain an arrangement, which directly reflects the arrangement of points on a
 * valid square boundary possible.
 *
 * In each case, after sorting, we obtain the following conclusion regarding the connections of the points:
 * 1. p0p1, p1p3, p3p2 and p2p0 form the four sides of any valid square.
 * 2. p0p3 and p1p2 form the diagonals of the square.
 *
 * Thus, once the sorting of the points is done, based on the above knowledge, we can directly compare p0p1, p1p3, p3p2
 * and p2p0 for equality of lengths(corresponding to the sides); and p0p3 and p1p2 for equality of lengths(corresponding
 * to the diagonals).
 */
public class ValidSquare {
    // Approach 1: Brute force
//    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
//        int[][] points = new int[][]{p1, p2, p3, p4};
//
//        long side = Long.MIN_VALUE;
//        long diag = Long.MIN_VALUE;
//
//        for (int i = 0; i < points.length; i++) {
//            for (int j = 0; j < points.length; j++) {
//                if (i != j) {
//                    long dist = (long)Math.pow((points[i][0] - points[j][0]), 2) +
//                            (long)Math.pow((points[i][1] - points[j][1]), 2);
//
//                    if (side == Long.MIN_VALUE || side == dist) {
//                        side = dist;
//                    } else if (diag == Long.MIN_VALUE || diag == dist) {
//                        diag = dist;
//                    } else if (dist != side && dist != diag) {
//                        return false;
//                    }
//                }
//            }
//        }
//
//        if (side != Integer.MIN_VALUE && diag != Integer.MIN_VALUE && side != 0 && diag != 0) {
//            return true;
//        }
//
//        return false;
//    }

    // Approach 2: Sorting
    public double dist(int[] p1, int[] p2) {
        return (p2[1] - p1[1]) * (p2[1] - p1[1]) + (p2[0] - p1[0]) * (p2[0] - p1[0]);
    }

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] p={p1,p2,p3,p4};
        Arrays.sort(p, (l1, l2) -> l2[0] == l1[0] ? l1[1] - l2[1] : l1[0] - l2[0]);
        return dist(p[0], p[1]) != 0 && dist(p[0], p[1]) == dist(p[1], p[3]) && dist(p[1], p[3]) == dist(p[3], p[2]) &&
                dist(p[3], p[2]) == dist(p[2], p[0])   && dist(p[0],p[3])==dist(p[1],p[2]);
    }
}
