package edu.codifyme.leetcode.practice.recursion;

import java.util.Set;

/**
 * MEDIUM: Knight Probability in Chessboard
 * https://leetcode.com/problems/knight-probability-in-chessboard/
 *
 * On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows
 * and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).
 *
 * A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal
 * direction, then one square in an orthogonal direction.
 *
 * Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would
 * go off the chessboard) and moves there.
 *
 * The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability
 * that the knight remains on the board after it has stopped moving.
 *
 * Example:
 * Input: 3, 2, 0, 0
 * Output: 0.0625
 * Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
 * From each of those positions, there are also two moves that will keep the knight on the board.
 * The total probability the knight stays on the board is 0.0625.
 *
 * Note:
 * N will be between 1 and 25.
 * K will be between 0 and 100.
 * The knight always initially starts on the board.
 *
 * Approach:
 * DP with memoization
 * Time Complexity: O(N^2 K) where N, KN,K are defined as in the problem. We do O(1) work on each layer dp of N^2
 * elements, and there are K layers considered
 *
 * Approach 2: Matrix Exponentiation [Accepted]
 * Intuition
 * The recurrence expressed in Approach #1 expressed states that transitioned to a linear combination of other states.
 * Any time this happens, we can represent the entire transition as a matrix of those linear combinations. Then, the
 * n-th power of this matrix represents the transition of n moves, and thus we can reduce the problem to a problem of
 * matrix exponentiation.
 *
 * Algorithm
 * First, there is a lot of symmetry on the board that we can exploit. Naively, there are N^2 possible states the
 * knight can be in (assuming it is on the board). Because of symmetry through the horizontal, vertical, and diagonal
 * axes, we can assume that the knight is in the top-left quadrant of the board, and that the column number is equal to
 * or larger than the row number. For any square, the square that is found by reflecting about these axes to satisfy
 * these conditions will be the canonical index of that square.
 *
 * This will reduce the number of states from N^2 to approximately {N^2}/8, which makes the following (cubic) matrix
 * exponentiation on this O({N^2}/8) * O({N^2}/8) matrix approximately 8^3 times faster.
 *
 * Now, if we know that every state becomes some linear combination of states after one move, then let's write a
 * transition matrix T of them, where the i-th row of T represents the linear combination of states that the i-th
 * state goes to. Then, T^n represents a transition of n moves, for which we want the sum of the i-th row, where i
 * is the index of the starting square.
 *
 * Complexity Analysis
 * Time Complexity: O(N^6 log(K)) where N, KN,K are defined as in the problem. There are approximately {N^2}/8
 * canonical states, which makes our matrix multiplication O(N^6). To find the KK-th power of this matrix, we make
 * O(log(K)) matrix multiplications.
 *
 * Space Complexity: O(N^4). The matrix has approximately {N^4}/64 elements.
 */
public class KnightProbabilityInChess {
    Set<int[]> moves = Set.of(new int[]{-1,-2},
            new int[]{1,-2},
            new int[]{2,-1},
            new int[]{2,1},
            new int[]{1,2},
            new int[]{-1,2},
            new int[]{-2,1},
            new int[]{-2,-1});
    int N;
    double[][][] dp;

    public double knightProbability(int N, int K, int r, int c) {
        this.N = N;

        dp = new double[N][N][K+1];
        return dfs(K, r, c);
    }

    double dfs(int K, int r, int c) {
        if (K == 0) {
            return 1;
        }

        if (dp[r][c][K] != 0.0) {
            return dp[r][c][K];
        }

        double count = 0;
        for (int[] move: moves) {
            if (isValid(r+move[0], c+move[1])) {
                count += dfs(K-1, r+move[0], c+move[1]);
            }
        }

        dp[r][c][K] = count/8.0;
        return dp[r][c][K];
    }

    boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    // Leetcode Solution DP code
//    public double knightProbability(int N, int K, int sr, int sc) {
//        double[][] dp = new double[N][N];
//        int[] dr = new int[]{2, 2, 1, 1, -1, -1, -2, -2};
//        int[] dc = new int[]{1, -1, 2, -2, 2, -2, 1, -1};
//
//        dp[sr][sc] = 1;
//        for (; K > 0; K--) {
//            double[][] dp2 = new double[N][N];
//            for (int r = 0; r < N; r++) {
//                for (int c = 0; c < N; c++) {
//                    for (int k = 0; k < 8; k++) {
//                        int cr = r + dr[k];
//                        int cc = c + dc[k];
//                        if (0 <= cr && cr < N && 0 <= cc && cc < N) {
//                            dp2[cr][cc] += dp[r][c] / 8.0;
//                        }
//                    }
//                }
//            }
//            dp = dp2;
//        }
//        double ans = 0.0;
//        for (double[] row: dp) {
//            for (double x: row) ans += x;
//        }
//        return ans;
//    }

    // Leetcode solution Matrix exponent code
//    public double knightProbability(int N, int K, int sr, int sc) {
//        int[] dr = new int[]{-1, -1, 1, 1, -2, -2, 2, 2};
//        int[] dc = new int[]{2, -2, 2, -2, 1, -1, 1, -1};
//
//        int[] index = new int[N * N];
//        int t = 0;
//        for (int r = 0; r < N; r++) {
//            for (int c = 0; c < N; c++) {
//                if (r * N + c == canonical(r, c, N)) {
//                    index[r * N + c] = t;
//                    t++;
//                } else {
//                    index[r * N + c] = index[canonical(r, c, N)];
//                }
//            }
//        }
//
//        double[][] T = new double[t][t];
//        int curRow = 0;
//        for (int r = 0; r < N; r++) {
//            for (int c = 0; c < N; c++) {
//                if (r * N + c == canonical(r, c, N)) {
//                    for (int k = 0; k < 8; k++) {
//                        int cr = r + dr[k], cc = c + dc[k];
//                        if (0 <= cr && cr < N && 0 <= cc && cc < N) {
//                            T[curRow][index[canonical(cr, cc, N)]] += 0.125;
//                        }
//                    }
//                    curRow++;
//                }
//            }
//        }
//
//        double[] row = matrixExpo(T, K)[index[sr*N + sc]];
//        double ans = 0.0;
//        for (double x: row) ans += x;
//        return ans;
//    }
//
//    public int canonical(int r, int c, int N) {
//        if (2*r > N) r = N-1-r;
//        if (2*c > N) c = N-1-c;
//        if (r > c) {
//            int t = r;
//            r = c;
//            c = t;
//        }
//        return r * N + c;
//    }
//    public double[][] matrixMult(double[][] A, double[][] B) {
//        double[][] ans = new double[A.length][A.length];
//        for (int i = 0; i < A.length; i++) {
//            for (int j = 0; j < B[0].length; j++) {
//                for (int k = 0; k < B.length; k++) {
//                    ans[i][j] += A[i][k] * B[k][j];
//                }
//            }
//        }
//        return ans;
//    }
//    public double[][] matrixExpo(double[][] A, int pow) {
//        double[][] ans = new double[A.length][A.length];
//        for (int i = 0; i < A.length; i++) ans[i][i] = 1;
//        if (pow == 0) return ans;
//        if (pow == 1) return A;
//        if (pow % 2 == 1) return matrixMult(matrixExpo(A, pow-1), A);
//        double[][] B = matrixExpo(A, pow / 2);
//        return matrixMult(B, B);
//    }
}
