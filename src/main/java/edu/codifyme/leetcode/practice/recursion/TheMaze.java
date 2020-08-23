package edu.codifyme.leetcode.practice.recursion;

/**
 * 490. The Maze
 * https://leetcode.com/problems/the-maze/
 *
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left
 * or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 *
 * Given the ball's start position, the destination and the maze, determine whether the ball could stop at the
 * destination.
 *
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the
 * borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
 *
 * Example 1:
 * Input 1: a maze represented by a 2D array
 *
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 *
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
 *
 * Output: true
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 *
 * Example 2:
 * Input 1: a maze represented by a 2D array
 *
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 *
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (3, 2)
 *
 * Output: false
 * Explanation: There is no way for the ball to stop at the destination.
 *
 * Note:
 * There is only one ball and one destination in the maze.
 * Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 * The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the
 * border of the maze are all walls.
 * The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 *
 * Approach:
 * DFS or BFS.
 * Go to a point and move to four direction until hit a wall, recur from the point where you stop
 */
public class TheMaze {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, start, destination, visited);
    }

    public boolean dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited) {
        if (visited[start[0]][start[1]])
            return false;
        if (start[0] == destination[0] && start[1] == destination[1])
            return true;
        visited[start[0]][start[1]] = true;
        int r = start[1] + 1, l = start[1] - 1, u = start[0] - 1, d = start[0] + 1;
        while (r < maze[0].length && maze[start[0]][r] == 0) // right
            r++;
        if (dfs(maze, new int[] {start[0], r - 1}, destination, visited))
            return true;
        while (l >= 0 && maze[start[0]][l] == 0) //left
            l--;
        if (dfs(maze, new int[] {start[0], l + 1}, destination, visited))
            return true;
        while (u >= 0 && maze[u][start[1]] == 0) //up
            u--;
        if (dfs(maze, new int[] {u + 1, start[1]}, destination, visited))
            return true;
        while (d < maze.length && maze[d][start[1]] == 0) //down
            d++;
        if (dfs(maze, new int[] {d - 1, start[1]}, destination, visited))
            return true;
        return false;
    }

//     int[][] moves = new int[][]{{-1,0}, {0,1}, {1,0}, {0,-1}};
//     boolean[][] dp;

//     public boolean hasPath(int[][] maze, int[] start, int[] destination) {
//         dp = new boolean[maze.length][maze[0].length];
//         return pathExists(maze, start[0], start[1], -1, destination);
//     }

//     boolean pathExists(int[][] maze, int row, int col, int dir, int[] dest) {
//         if (row == dest[0] && col == dest[1]) {
//             return true;
//         }

//         if (dp[row][col]) {
//             return false;
//         }
//         dp[row][col] = true;

//         // Move in all directions except coming direction
//         // when hit wall / block, recur
//         for (int k = 0; k < moves.length; k++) {
//             // Skip if same direction or opposite direction
//             if (dir != -1 && (k == dir || Math.abs(k - dir) == 2)) {
//                 continue;
//             }

//             int i = row;
//             int j = col;

//             while (isValid(maze, i + moves[k][0], j + moves[k][1])) {
//                 i += moves[k][0];
//                 j += moves[k][1];
//             }

//             if (i != row || j != col) {
//                 if ( pathExists(maze, i, j, k, dest) ) {
//                     return true;
//                 }
//             }
//         }

//         return false;
//     }

//     boolean isValid(int[][] maze, int i, int j) {
//         if (i < 0 || i >= maze.length || j < 0 || j >= maze[0].length || maze[i][j] == 1) {
//             return false;
//         }
//         return true;
//     }
}
