package edu.codifyme.leetcode.interview.mocktest.google.array;

/**
 * MEDIUM: Longest Line of Consecutive One in Matrix
 * https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/
 *
 * Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical,
 * diagonal or anti-diagonal.
 * Example:
 * Input:
 * [[0,1,1,0],
 *  [0,1,1,0],
 *  [0,0,0,1]]
 * Output: 3
 * Hint: The number of elements in the given matrix will not exceed 10,000.
 *
 * Approach:
 * Brute force, browse through all possible direction and get the Max
 *
 * Better Approach:
 * Use a 3D DP, 3rd dimension is for each direction (size 4)
 *
 * Even Better approach:
 * As every line depends only on previous line, rewrite on it
 */
public class LongestLineOfConsecutiveOne {
    public static int HOR = 0;
    public static int VER = 1;
    public static int DIAG = 2;
    public static int ADIAG = 3;

    public int longestLine(int[][] M) {
        if (M.length < 1) {
            return 0;
        }

        int[][] dp = new int[M[0].length+2][4];
        int max = 0;

        for (int i = 1; i < M.length+1; i++) {
            int old = 0;
            for (int j = 1; j < M[0].length+1; j++) {
                if (M[i-1][j-1] == 1) {
                    // Horizontal
                    dp[j][HOR] = dp[j-1][HOR]+1;
                    dp[j][VER] = dp[j][VER]+1;

                    int prev = dp[j][DIAG];
                    dp[j][DIAG] = old + 1;
                    old = prev;

                    dp[j][ADIAG] = dp[j+1][ADIAG]+1;
                    max = Math.max(max, Math.max(Math.max(dp[j][HOR], dp[j][VER]), Math.max(dp[j][DIAG], dp[j][ADIAG])));
                } else {
                    old = dp[j][DIAG];
                    dp[j][HOR] = 0;
                    dp[j][VER] = 0;
                    dp[j][DIAG] = 0;
                    dp[j][ADIAG] = 0;
                }
            }
        }

        return max;
    }

    // 2D DP; vert starting from right, so, no need of prev state
//    public int longestLine(int[][] M) {
//        if (M.length == 0) return 0;
//        int n = M[0].length;
//        int[] hor = new int[n];
//        int[] vert = new int[n];
//        int[] diag = new int[n];
//        int[] verDia = new int[n];
//        int res = 0;
//        for (int i = 0; i < M.length; i++) {
//            int[] one = new int[n];
//            int[] two = new int[n];
//            for (int j = 0; j < M[0].length; j++) {
//                if (M[i][j] == 1) {
//                    hor[j] = j == 0 ? 1 : hor[j - 1] + 1;
//                    vert[j] = vert[j] + 1;
//                    one[j] = j == 0 ? 1 : diag[j - 1] + 1;
//                    two[j] = j == n - 1 ? 1 :verDia[j + 1] + 1;
//                    res = Math.max(Math.max(res,hor[j]),Math.max(vert[j],one[j]));
//                    res = Math.max(res,two[j]);
//                } else {
//                    hor[j] = vert[j] = 0;
//                }
//            }
//            diag = one;
//            verDia = two;
//
//        }
//        return res;
//    }

    // BRUTE Force
//    public int longestLine(int[][] M) {
//        if (M.length == 0) return 0;
//        int ones = 0;
//        // horizontal
//        for (int i = 0; i < M.length; i++) {
//            int count = 0;
//            for (int j = 0; j < M[0].length; j++) {
//                if (M[i][j] == 1) {
//                    count++;
//                    ones = Math.max(ones, count);
//                } else count = 0;
//            }
//        }
//        // vertical
//        for (int i = 0; i < M[0].length; i++) {
//            int count = 0;
//            for (int j = 0; j < M.length; j++) {
//                if (M[j][i] == 1) {
//                    count++;
//                    ones = Math.max(ones, count);
//                } else count = 0;
//            }
//        }
//        // upper diagonal
//        for (int i = 0; i < M[0].length || i < M.length; i++) {
//            int count = 0;
//            for (int x = 0, y = i; x < M.length && y < M[0].length; x++, y++) {
//                if (M[x][y] == 1) {
//                    count++;
//                    ones = Math.max(ones, count);
//                } else count = 0;
//            }
//        }
//        // lower diagonal
//        for (int i = 0; i < M[0].length || i < M.length; i++) {
//            int count = 0;
//            for (int x = i, y = 0; x < M.length && y < M[0].length; x++, y++) {
//                if (M[x][y] == 1) {
//                    count++;
//                    ones = Math.max(ones, count);
//                } else count = 0;
//            }
//        }
//        // upper anti-diagonal
//        for (int i = 0; i < M[0].length || i < M.length; i++) {
//            int count = 0;
//            for (int x = 0, y = M[0].length - i - 1; x < M.length && y >= 0; x++, y--) {
//                if (M[x][y] == 1) {
//                    count++;
//                    ones = Math.max(ones, count);
//                } else count = 0;
//            }
//        }
//        // lower anti-diagonal
//        for (int i = 0; i < M[0].length || i < M.length; i++) {
//            int count = 0;
//            for (int x = i, y = M[0].length - 1; x < M.length && y >= 0; x++, y--) {
//                // System.out.println(x+" "+y);
//                if (M[x][y] == 1) {
//                    count++;
//                    ones = Math.max(ones, count);
//                } else count = 0;
//            }
//        }
//        return ones;
//    }
}
