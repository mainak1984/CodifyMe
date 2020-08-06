package edu.codifyme.leetcode.interview.google.treesngrapsh;

/**
 * MEDIUM:
 * https://leetcode.com/problems/number-of-islands
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water
 * and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are
 * all surrounded by water.
 *
 * Example 1:
 *
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Output: 1
 * Example 2:
 *
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Output: 3
 */
public class NumberOfIsland {
    public int numIslands(char[][] grid) {
        if ( null == grid || grid.length <= 0 || grid[0].length <= 0 ) {
            return 0;
        }
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if ( !visited[i][j] && grid[i][j] == '1') {
                    search(grid, visited, i, j);
                    count += 1;
                }
            }
        }
        return count;
    }

    public void search(char[][] grid, boolean[][] visited, int i, int j) {
        if ( i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || '0' == grid[i][j] || visited[i][j]) {
            return;
        }

        visited[i][j] = true;

        search(grid, visited, i+1, j);
        search(grid, visited, i, j+1);
        search(grid, visited, i-1, j);
        search(grid, visited, i, j-1);
    }
}
