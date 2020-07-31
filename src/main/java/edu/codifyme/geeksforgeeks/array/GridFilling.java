package edu.codifyme.geeksforgeeks.array;

/**
 * EASY: Given a matrix of ‘O’ and ‘X’, replace ‘O’ with ‘X’ if surrounded by ‘X’
 * https://www.geeksforgeeks.org/given-matrix-o-x-replace-o-x-surrounded-x/
 *
 * Given a matrix where every element is either ‘O’ or ‘X’, replace ‘O’ with ‘X’ if surrounded by ‘X’. A ‘O’ (or a set
 * of ‘O’) is considered to be by surrounded by ‘X’ if there are ‘X’ at locations just below, just above, just left and
 * just right of it.
 *
 * Examples:
 *
 * Input: mat[M][N] =  {{'X', 'O', 'X', 'X', 'X', 'X'},
 *                      {'X', 'O', 'X', 'X', 'O', 'X'},
 *                      {'X', 'X', 'X', 'O', 'O', 'X'},
 *                      {'O', 'X', 'X', 'X', 'X', 'X'},
 *                      {'X', 'X', 'X', 'O', 'X', 'O'},
 *                      {'O', 'O', 'X', 'O', 'O', 'O'},
 *                     };
 * Output: mat[M][N] =  {{'X', 'O', 'X', 'X', 'X', 'X'},
 *                       {'X', 'O', 'X', 'X', 'X', 'X'},
 *                       {'X', 'X', 'X', 'X', 'X', 'X'},
 *                       {'O', 'X', 'X', 'X', 'X', 'X'},
 *                       {'X', 'X', 'X', 'O', 'X', 'O'},
 *                       {'O', 'O', 'X', 'O', 'O', 'O'},
 *                     };
 *
 * Input: mat[M][N] =  {{'X', 'X', 'X', 'X'}
 *                      {'X', 'O', 'X', 'X'}
 *                      {'X', 'O', 'O', 'X'}
 *                      {'X', 'O', 'X', 'X'}
 *                      {'X', 'X', 'O', 'O'}
 *                     };
 * Input: mat[M][N] =  {{'X', 'X', 'X', 'X'}
 *                      {'X', 'X', 'X', 'X'}
 *                      {'X', 'X', 'X', 'X'}
 *                      {'X', 'X', 'X', 'X'}
 *                      {'X', 'X', 'O', 'O'}
 *                     };
 *
 * Solution:
 * Mark all the ‘O’ which has a connection with boundary by starting a DFS from all the edges and replace them with ‘-’
 * Change rest of the ‘O’ characters with ‘X’
 * Revert back the ‘-’ to ‘O’
 *
 * Complexity: O(n)
 */
public class GridFilling {
    static int M = 6;
    static int N = 6;

    // Returns size of maximum
    // size subsquare matrix
    // surrounded by 'X'
    static void replaceSurrounded(char mat[][])
    {
        // Step 1: Replace
        // all 'O' with '-'
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                if (mat[i][j] == 'O')
                    mat[i][j] = '-';

        // Call floodFill for
        // all '-' lying on edges
        for (int i = 0; i < M; i++) // Left side
            if (mat[i][0] == '-')
                floodFillUtil(mat, i, 0,'-', 'O');
        for (int i = 0; i < M; i++) // Right side
            if (mat[i][N - 1] == '-')
                floodFillUtil(mat, i, N - 1,'-', 'O');
        for (int i = 0; i < N; i++) // Top side
            if (mat[0][i] == '-')
                floodFillUtil(mat, 0, i,'-', 'O');
        for (int i = 0; i < N; i++) // Bottom side
            if (mat[M - 1][i] == '-')
                floodFillUtil(mat, M - 1, i, '-', 'O');

        // Step 3: Replace
        // all '-' with 'X'
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                if (mat[i][j] == '-')
                    mat[i][j] = 'X';
    }

    static void floodFillUtil(char mat[][], int x, int y, char prevV, char newV) {
        // Base cases
        if (x < 0 || x >= M || y < 0 || y >= N)
            return;

        if (mat[x][y] != prevV)
            return;

        // Replace the color at (x, y)
        mat[x][y] = newV;

        // Recur for north,
        // east, south and west
        floodFillUtil(mat, x + 1, y, prevV, newV);
        floodFillUtil(mat, x - 1, y, prevV, newV);
        floodFillUtil(mat, x, y + 1, prevV, newV);
        floodFillUtil(mat, x, y - 1, prevV, newV);
    }
}
