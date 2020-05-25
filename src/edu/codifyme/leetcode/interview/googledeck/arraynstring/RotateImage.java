package edu.codifyme.leetcode.interview.googledeck.arraynstring;

/**
 * MEDIUM:
 * https://leetcode.com/explore/interview/card/google/59/array-and-strings/3052/
 *
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 *
 * Example 1:
 * Given input matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 *
 * Example 2:
 * Given input matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 *
 * Approach 1 : Transpose and then reverse
 * The obvious idea would be to transpose the matrix first and then reverse each row. This simple approach already
 * demonstrates the best possible time complexity O(N^2)
 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        if (0 == matrix.length) {
            return;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        for (int i = 0; i < row; i++ ) {
            for (int j = i; j < col; j++ ) {
                int temp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < (col + 1)/2; j++ ) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][col-j-1];
                matrix[i][col-j-1] = temp;
            }
        }
    }

    // APPROACH 2: one pass solution
//    public void rotate(int[][] matrix) {
//        if(matrix==null || matrix.length<=1){
//            return;
//        }
//        int n = matrix.length;
//        for(int i=0; i<n/2; i++){
//            int left = i;
//            int right = n-2-i;
//            for(int j=left; j<=right; j++){
//                int tmp = matrix[i][j];
//                matrix[i][j] = matrix[n-1-j][i];
//                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
//                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
//                matrix[j][n-1-i] = tmp;
//            }
//        }
//        return;
//    }
}
