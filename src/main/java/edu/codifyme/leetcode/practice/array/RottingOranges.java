package edu.codifyme.leetcode.practice.array;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 994. Rotting Oranges
 * MEDIUM: https://leetcode.com/problems/rotting-oranges/
 *
 * In a given grid, each cell can have one of three values:
 *
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible,
 * return -1 instead.
 *
 * Example 1:
 * Input: [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 *
 * Example 2:
 * Input: [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens
 * 4-directionally.
 *
 * Example 3:
 * Input: [[0,2]]
 * Output: 0
 * Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 *
 * Note:
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * grid[i][j] is only 0, 1, or 2.
 *
 * Approach 1:
 * BFS: - We may use the same array for storing visited also
 *
 * Approach 2:
 * Traverse through the array for each round of rotting, avoids queue
 */
public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        int[] X = {0,0,1,-1};
        int[] Y = {1,-1,0,0};
        int freshCount=0;

        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    freshCount++;
                } else if(grid[i][j]==2){
                    queue.offer(new int[]{i,j});
                }
            }
        }

        int days=0;
        while(!queue.isEmpty() && freshCount>0){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int[] curr = queue.poll();
                for(int k=0;k<4;k++){
                    int dx = curr[0]+X[k];
                    int dy = curr[1]+Y[k];
                    if(dx<0||dx>=grid.length||dy<0||dy>=grid[0].length||grid[dx][dy]!=1){
                        continue;
                    }

                    grid[dx][dy] = 2;
                    queue.offer(new int[]{dx,dy});
                    freshCount--;
                }

            }
            days++;
        }
        return freshCount==0?days:-1;
    }
}
