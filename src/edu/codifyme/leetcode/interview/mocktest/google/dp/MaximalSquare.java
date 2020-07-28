package edu.codifyme.leetcode.interview.mocktest.google.dp;

/**
 * MEDIUM: Maximal Square
 * https://leetcode.com/problems/maximal-square/
 *
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 * Example:
 * Input:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * Output: 4
 *
 * Approach:
 * Build a bottom up array.
 * If any position is 1, make its value min(left, up, corner-left) + 1
 * scan whole array for lasgest number for determining one side of square
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        char max = 0;

        if (matrix.length == 0) {
            return 0;
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    int a = matrix[i-1][j] - '0';
                    int b = matrix[i-1][j-1] - '0';
                    int c = matrix[i][j-1] - '0';
                    int min = Math.min(Math.min(a, b), c);
                    matrix[i][j] += min;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = matrix[i][j] > max? matrix[i][j]: max;
            }
        }

        int maxVal = max - '0';

        return maxVal * maxVal;
    }

    // Efficient implementation
//    public int maximalSquare(char[][] matrix) {
//        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
//        int[][] dp = new int[rows + 1][cols + 1];
//        int maxsqlen = 0;
//        for (int i = 1; i <= rows; i++) {
//            for (int j = 1; j <= cols; j++) {
//                if (matrix[i-1][j-1] == '1'){
//                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
//                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
//                }
//            }
//        }
//        return maxsqlen * maxsqlen;
//    }

    // More eeficient DP
//    In the previous approach for calculating dp of ith row we are using only the previous element and the (i-1)th row.
//    Therefore, we don't need 2D dp matrix as 1D dp array will be sufficient for this.
//    Initially the dp array contains all 0's. As we scan the elements of the original matrix across a row, we keep on
//    updating the dp array as per the equation dp[j]=min(dp[j-1],dp[j],prev), where prev refers to the old dp[j-1]].
//    For every row, we repeat the same process and update in the same dp array.
//    
//    public int maximalSquare(char[][] matrix) {
//        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
//        int[] dp = new int[cols + 1];
//        int maxsqlen = 0, prev = 0;
//        for (int i = 1; i <= rows; i++) {
//            for (int j = 1; j <= cols; j++) {
//                int temp = dp[j];
//                if (matrix[i - 1][j - 1] == '1') {
//                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
//                    maxsqlen = Math.max(maxsqlen, dp[j]);
//                } else {
//                    dp[j] = 0;
//                }
//                prev = temp;
//            }
//        }
//        return maxsqlen * maxsqlen;
//    }
}
