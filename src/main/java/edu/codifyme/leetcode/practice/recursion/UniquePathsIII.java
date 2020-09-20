package edu.codifyme.leetcode.practice.recursion;

/**
 * 980. Unique Paths III
 * HARD: https://leetcode.com/problems/unique-paths-iii/
 *
 * On a 2-dimensional grid, there are 4 types of squares:
 *
 * 1 represents the starting square.  There is exactly one starting square.
 * 2 represents the ending square.  There is exactly one ending square.
 * 0 represents empty squares we can walk over.
 * -1 represents obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every
 * non-obstacle square exactly once.
 *
 * Example 1:
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 *
 * Example 2:
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * Output: 4
 * Explanation: We have the following four paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 *
 * Example 3:
 * Input: [[0,1],[2,0]]
 * Output: 0
 * Explanation:
 * There is no path that walks over every empty square exactly once.
 * Note that the starting and ending square can be anywhere in the grid.
 *
 * Note:
 * 1 <= grid.length * grid[0].length <= 20
 *
 * Approach: Backtracking / DFS
 * First find out where the start and the end is.
 * Also We need to know the number of empty cells.
 *
 * We we try to explore a cell,
 * it will change 0 to -2 and do a dfs in 4 direction.
 *
 * If we hit the target and pass all empty cells, increment the result.
 */
public class UniquePathsIII {
    public static int VISITED = 4;
    int total = 0;

    public int uniquePathsIII(int[][] grid) {
        int count = 1, startX = 0, startY = 0;
        total = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    count++;
                } else if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                }
            }
        }

        traverse(grid, startX, startY, 0, count);

        return total;
    }

    void traverse(int[][] grid, int x, int y, int visited, int totalCount) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == -1 || grid[x][y] == VISITED ||
                (grid[x][y] == 2 && visited != totalCount)) {
            return;
        }
        if (grid[x][y] == 2 && visited == totalCount) {
            total++;
            return;
        }

        int temp = grid[x][y];
        grid[x][y] = VISITED;

        traverse(grid, x + 1, y, visited + 1, totalCount);
        traverse(grid, x, y + 1, visited + 1, totalCount);
        traverse(grid, x - 1, y, visited + 1, totalCount);
        traverse(grid, x, y - 1, visited + 1, totalCount);

        grid[x][y] = temp;
    }
}
