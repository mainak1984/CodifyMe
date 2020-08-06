package edu.codifyme.leetcode.interview.google.treesngrapsh;

/**
 * HARD:
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix
 *
 * Given an integer matrix, find the length of the longest increasing path.
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move
 * outside of the boundary (i.e. wrap-around is not allowed).
 *
 * Example 1:
 * Input: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 *
 * Example 2:
 * Input: nums =
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 *
 * Approach: Recursion + Memoization
 */
public class LongestIncreasingPathInMatrix {

    private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int m, n;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        m = matrix.length; n = matrix[0].length;
        int[][] cache = new int[m][n];
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                ans = Math.max(ans, dfs(matrix, i, j, cache));
        return ans;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] cache) {
        if (cache[i][j] != 0) return cache[i][j];
        for (int[] d : dirs) {
            int x = i + d[0], y = j + d[1];
            if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j])
                cache[i][j] = Math.max(cache[i][j], dfs(matrix, x, y, cache));
        }
        return ++cache[i][j];
    }

    // Alternate code
//    public static int VISITED = Integer.MIN_VALUE;
//    int[][] dp;
//
//    public int longestIncreasingPath(int[][] matrix) {
//        if (null == matrix || matrix.length == 0) {
//            return 0;
//        }
//
//        dp = new int[matrix.length][matrix[0].length];
//        int result = 0;
//
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                if(dp[i][j] == 0) {
//                    result = Math.max(result, dfs(matrix, i, j, Integer.MIN_VALUE));
//                }
//            }
//        }
//
//        return result;
//    }
//
//    int dfs(int[][] matrix, int i, int j, int prevNum) {
//        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] == VISITED || matrix[i][j] <= prevNum) {
//            return 0;
//        }
//
//        if (dp[i][j] != 0) {
//            return dp[i][j];
//        }
//
//        int val = matrix[i][j];
//        matrix[i][j] = VISITED;
//
//        int d = dfs(matrix, i+1, j, val);
//        int r = dfs(matrix, i, j+1, val);
//        int l = dfs(matrix, i, j-1, val);
//        int u = dfs(matrix, i-1, j, val);
//
//        int res = Math.max(Math.max(d,r), Math.max(l,u))+1;
//        dp[i][j] = res;
//
//        matrix[i][j] = val;
//
//        return res;
//    }
}
