package edu.codifyme.leetcode.practice.dp;

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
}
