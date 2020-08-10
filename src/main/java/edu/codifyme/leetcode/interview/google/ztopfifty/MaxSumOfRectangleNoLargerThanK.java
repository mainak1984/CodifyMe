package edu.codifyme.leetcode.interview.google.ztopfifty;

/**
 * 363. Max Sum of Rectangle No Larger Than K
 * HARD: https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
 *
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum
 * is no larger than k.
 *
 * Example:
 * Input: matrix = [[1,0,1],[0,-2,3]], k = 2
 * Output: 2
 * Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
 *              and 2 is the max number no larger than k (k = 2).
 *
 * Note:
 * The rectangle inside the matrix must have an area > 0.
 * What if the number of rows is much larger than the number of columns?
 */
public class MaxSumOfRectangleNoLargerThanK {
    // Best Approach: O(n^3); as it applies maximum sub-array for finding area value
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 ||
                matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int res = Integer.MIN_VALUE;

        for (int c = 0; c < col; c++) {
            int[] sum = new int[row];
            for (int l = c; l < col; l++) {

                for (int r = 0; r < row; r++) sum[r] += matrix[r][l];//horozontal accumulate sum

                //max subarray sum
                int cur = 0, max = sum[0];
                for (int ele : sum) {
                    cur = Math.max(cur + ele, ele);
                    max = Math.max(max, cur);
                    if (max == k) return k;
                }
                if (max < k) res = Math.max(res, max);
                else {//max larger than k, check every vertical subarray
                    for (int i = 0; i < row; i++) {
                        int curSum = 0;
                        for (int j = i; j < row; j++) {
                            curSum += sum[j];
                            if (curSum <= k) res = Math.max(res, curSum);
                        }
                    }
                    if (res == k) return k;
                }
            }
        }
        return res;
    }

    // Second best approach: Uses Sorted set to find the value of one area offsetting the current one (avoids linear
    // search
//    public int maxSumSubmatrix(int[][] matrix, int k) {
//        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
//            return 0;
//        int rows = matrix.length, cols = matrix[0].length;
//        int[][] areas = new int[rows][cols];
//        for (int r = 0; r < rows; r++) {
//            for (int c = 0; c < cols; c++) {
//                int area = matrix[r][c];
//                if (r-1 >= 0)
//                    area += areas[r-1][c];
//                if (c-1 >= 0)
//                    area += areas[r][c-1];
//                if (r-1 >= 0 && c-1 >= 0)
//                    area -= areas[r-1][c-1];
//                areas[r][c] = area;
//            }
//        }
//        int max = Integer.MIN_VALUE;
//        for (int r1 = 0; r1 < rows; r1++) {
//            for (int r2 = r1; r2 < rows; r2++) {
//                TreeSet<Integer> tree = new TreeSet<>();
//                tree.add(0);    // padding
//                for (int c = 0; c < cols; c++) {
//                    int area = areas[r2][c];
//                    if (r1-1 >= 0)
//                        area -= areas[r1-1][c];
//                    Integer ceiling = tree.ceiling(area - k);
//                    if (ceiling != null)
//                        max = Math.max(max, area - ceiling);
//                    tree.add(area);
//                }
//            }
//        }
//        return max;
//    }

    // Naive O(n^4)
//    public int maxSumSubmatrix(int[][] matrix, int k) {
//        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
//            return 0;
//        int rows = matrix.length, cols = matrix[0].length;
//        int[][] areas = new int[rows][cols];
//        for (int r = 0; r < rows; r++) {
//            for (int c = 0; c < cols; c++) {
//                int area = matrix[r][c];
//                if (r-1 >= 0)
//                    area += areas[r-1][c];
//                if (c-1 >= 0)
//                    area += areas[r][c-1];
//                if (r-1 >= 0 && c-1 >= 0)
//                    area -= areas[r-1][c-1];
//                areas[r][c] = area;
//            }
//        }
//        int max = Integer.MIN_VALUE;
//        for (int r1 = 0; r1 < rows; r1++) {
//            for (int c1 = 0; c1 < cols; c1++) {
//                for (int r2 = r1; r2 < rows; r2++) {
//                    for (int c2 = c1; c2 < cols; c2++) {
//                        int area = areas[r2][c2];
//                        if (r1-1 >= 0)
//                            area -= areas[r1-1][c2];
//                        if (c1-1 >= 0)
//                            area -= areas[r2][c1-1];
//                        if (r1-1 >= 0 && c1 -1 >= 0)
//                            area += areas[r1-1][c1-1];
//                        if (area <= k)
//                            max = Math.max(max, area);
//                    }
//                }
//            }
//        }
//        return max;
//    }
}
