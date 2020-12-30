package edu.codifyme.leetcode.practice.array;

/**
 * 289. Game of Life
 * MEDIUM: https://leetcode.com/problems/game-of-life/
 *
 * According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by
 * the British mathematician John Horton Conway in 1970."
 *
 * The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or
 * dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the
 * following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population.
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * The next state is created by applying the above rules simultaneously to every cell in the current state, where births
 * and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.
 *
 * Example 1:
 * Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 *
 * Example 2:
 * Input: board = [[1,1],[1,0]]
 * Output: [[1,1],[1,1]]
 *
 * Constraints:
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 25
 * board[i][j] is 0 or 1.
 *
 * Follow up:
 * Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot update some cells
 * first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause
 * problems when the active area encroaches upon the border of the array (i.e., live cells reach the border). How would
 * you address these problems?
 *
 * Approach: Mark the items with special digit as per the current sate, convert the current state to proper value
 */
public class GameOfLife {
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    int noOfNeighbours = findLiveNeighbour(board, i, j);

                    if (noOfNeighbours < 2 || noOfNeighbours > 3) {
                        board[i][j] += 10;
                    }
                } else if (board[i][j] == 0 && 3 == findLiveNeighbour(board, i, j)) {
                    board[i][j] += 10;
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] >= 10) {
                    board[i][j] = 1 - (board[i][j] % 10);
                }
            }
        }
    }

    int findLiveNeighbour(int[][] board, int row, int col) {
        int count = 0;

        for (int i = (row > 0? row-1: row); i <= (row == board.length-1 ? row: row+1); i++) {
            for (int j = (col > 0? col-1: col); j <= (col == board[0].length-1 ? col: col+1); j++) {
                if ((i != row || j != col) && board[i][j] % 10 == 1) {
                    count++;
                }
            }
        }

        return count;
    }
}
