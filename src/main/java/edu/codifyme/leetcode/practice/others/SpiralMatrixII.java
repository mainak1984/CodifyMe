package edu.codifyme.leetcode.practice.others;

/**
 * 59. Spiral Matrix II
 * MEDIUM: https://leetcode.com/problems/spiral-matrix-ii/
 *
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 *
 * Example 1:
 * 1 -> 2 -> 3
 *           V
 * 8 -> 9    4
 * ^         V
 * 7 <- 6 <- 5
 *
 * Input: n = 3
 * Output: [[1,2,3],[8,9,4],[7,6,5]]
 *
 * Example 2:
 * Input: n = 1
 * Output: [[1]]
 *
 * Constraints:
 * 1 <= n <= 20
 *
 * Approach 1: Traverse Layer by Layer in Spiral Form
 * Intuition
 * If we try to build a pattern for a given nn, we observe that the pattern repeats after completing one circular
 * traversal around the matrix. Let's call this one circular traversal as layer. We start traversing from the outer layer
 * and move towards inner layers on every iteration.
 *
 * Approach 2: Optimized spiral traversal
 * Intuition
 * Our main aim is to walk in a spiral form and fill the array in a particular pattern. In the previous approach, we
 * used a separate loop for each direction. Here, we discuss another optimized to achieve the same result.
 */
public class SpiralMatrixII {
    // Approach 1:
    public int[][] generateMatrix1(int n) {
        int[][] result = new int[n][n];
        int cnt = 1;
        for (int layer = 0; layer < (n + 1) / 2; layer++) {
            // direction 1 - traverse from left to right
            for (int ptr = layer; ptr < n - layer; ptr++) {
                result[layer][ptr] = cnt++;
            }
            // direction 2 - traverse from top to bottom
            for (int ptr = layer + 1; ptr < n - layer; ptr++) {
                result[ptr][n - layer - 1] = cnt++;
            }
            // direction 3 - traverse from right to left
            for (int ptr = layer + 1; ptr < n - layer; ptr++) {
                result[n - layer - 1][n - ptr - 1] = cnt++;
            }
            // direction 4 - traverse from bottom to top
            for (int ptr = layer + 1; ptr < n - layer - 1; ptr++) {
                result[n - ptr - 1][layer] = cnt++;
            }
        }
        return result;
    }

    // Approach 2:
    public int[][] generateMatrix2(int n) {
        int[][] result = new int[n][n];
        int cnt = 1;
        int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int d = 0;
        int row = 0;
        int col = 0;
        while (cnt <= n * n) {
            result[row][col] = cnt++;
            int r = Math.floorMod(row + dir[d][0], n);
            int c = Math.floorMod(col + dir[d][1], n);

            // change direction if next cell is non zero
            if (result[r][c] != 0) d = (d + 1) % 4;

            row += dir[d][0];
            col += dir[d][1];
        }
        return result;
    }

    // Approach 1: another implement
    public int[][] generateMatrix1_backup(int n) {
        int loop = 0;
        int num = 1;

        int[][] res = new int[n][n];

        if (n == 1) {
            res[0][0] = 1;
            return res;
        }

        while (loop < n-1) {
            for (int j = loop; j < n - loop; j++) {
                res[loop][j] = num++;
            }
            for (int i = loop+1; i < n - loop; i++) {
                res[i][n-1-loop] = num++;
            }
            for (int j = n - 2 -loop; j >= loop; j--) {
                res[n-1-loop][j] = num++;
            }
            for (int i = n - 2 -loop; i >= loop+1; i--) {
                res[i][loop] = num++;
            }

            loop++;
        }

        return res;
    }
}
