package edu.codifyme.leetcode.practice.array;

/**
 * 832. Flipping an Image
 * EASY: https://leetcode.com/problems/flipping-an-image/
 *
 * Given a binary matrix A, we want to flip the image horizontally, then invert it, and return the resulting image.
 *
 * To flip an image horizontally means that each row of the image is reversed.  For example, flipping [1, 1, 0]
 * horizontally results in [0, 1, 1].
 *
 * To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0. For example, inverting [0, 1, 1]
 * results in [1, 0, 0].
 *
 * Example 1:
 * Input: [[1,1,0],[1,0,1],[0,0,0]]
 * Output: [[1,0,0],[0,1,0],[1,1,1]]
 * Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
 * Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
 *
 * Example 2:
 * Input: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * Explanation: First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].
 * Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 *
 * Notes:
 * 1 <= A.length = A[0].length <= 20
 * 0 <= A[i][j] <= 1
 *
 * Approach:
 * We can do this in place. In each row, the ith value from the left is equal to the inverse of the ith value from the
 * right.
 * We use (C+1) / 2 (with floor division) to iterate over all indexes i in the first half of the row, including the center.
 */
public class FlippingAnImage {
    public int[][] flipAndInvertImage(int[][] A) {
        int row  = A.length;
        int col = A[0].length;
        int mid = (col + 1) / 2;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < mid; j++) {
                int temp = 1 - A[i][j];
                A[i][j] = 1 - A[i][col-j-1];
                A[i][col-j-1] = temp;
            }
        }

        return A;
    }
}
