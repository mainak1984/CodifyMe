package edu.codifyme.leetcode.interview.googledeck.treesngrapsh;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * HARD:
 * https://leetcode.com/explore/interview/card/google/61/trees-and-graphs/3072/
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

//    public int longestIncreasingPath(int[][] matrix) {
//        if (null == matrix || 0 == matrix.length) {
//            return 0;
//        }
//
//        int[][] visited = new int[matrix.length][matrix[0].length];
//
//        for(int i=0; i < matrix.length; i++) {
//            for(int j=0; j < matrix[0].length; j++) {
//                if ( 0 == visited[i][j] ) {
//                    findPath(matrix, i, j, Integer.MIN_VALUE, visited, new HashSet<Pair<Integer, Integer>>());
//                }
//            }
//        }
//
//        return maxSoFar;
//    }
//
//    int maxSoFar = 0;
//
//    public int findPath(int[][] matrix, int i, int j, int prev, int[][] visited, Set<Pair<Integer, Integer>> currentStack) {
//        if ( i < 0 || i > matrix.length-1 || j <0 || j > matrix[0].length - 1) {
//            return 0;
//        }
//        if ( prev >= matrix[i][j] ) {
//            return 0;
//        }
//        if (visited[i][j] != 0) {
//            if (currentStack.contains(new Pair<>(i, j))) {
//                return 0;
//            } else {
//                return visited[i][j];
//            }
//        }
//
//        visited[i][j] = 1;
//        currentStack.add(new Pair<>(i, j));
//
//        int right = findPath(matrix, i, j+1, matrix[i][j], visited, currentStack);
//        int down = findPath(matrix, i+1, j, matrix[i][j], visited, currentStack);
//        int left = findPath(matrix, i, j-1, matrix[i][j], visited, currentStack);
//        int up = findPath(matrix, i-1, j, matrix[i][j], visited, currentStack);
//
//        currentStack.remove(new Pair<>(i, j));
//
//        int maxLenFromHere = Math.max(Math.max(right, down), Math.max(left,up)) + 1;
//
//        maxSoFar = Math.max(maxSoFar, maxLenFromHere);
//
//        visited[i][j] = maxLenFromHere;
//
//        return maxLenFromHere;
//    }
}
