package edu.codifyme.leetcode.practice.searchnsort;

/**
 * 74. Search a 2D Matrix
 * MEDIUM: https://leetcode.com/problems/search-a-2d-matrix/
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 *
 * Example 1:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,50]], target = 3
 * Output: true
 *
 * Example 2:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,50]], target = 13
 * Output: false
 *
 * Example 3:
 * Input: matrix = [], target = 0
 * Output: false
 *
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 0 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 *
 * Approach:
 * The algorithm is a standard binary search :
 * - Initialise left and right indexes left = 0 and right = m x n - 1.
 * - While left <= right :
 *      - Pick up the index in the middle of the virtual array as a pivot index : pivot_idx = (left + right) / 2.
 *      - The index corresponds to row = pivot_idx // n and col = pivot_idx % n in the initial matrix, and hence one
 *      could get the pivot_element. This element splits the virtual array in two parts.
 *      - Compare pivot_element and target to identify in which part one has to look for target.
 */
public class SearchA2DMatrix {
    // Efficient implementation:
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0)
            return false;
        int n = matrix[0].length;

        // binary search
        int left = 0, right = m * n - 1;
        int pivotIdx, pivotElement;
        while (left <= right) {
            pivotIdx = (left + right) / 2;
            pivotElement = matrix[pivotIdx / n][pivotIdx % n];
            if (target == pivotElement)
                return true;
            else {
                if (target < pivotElement)
                    right = pivotIdx - 1;
                else
                    left = pivotIdx + 1;
            }
        }
        return false;
    }

    // Another implementation: find the row first and binary search into the column
//    public boolean searchMatrix(int[][] matrix, int target) {
//        if (matrix.length == 0 || matrix[0].length == 0 || target < matrix[0][0] ||
//                target > matrix[matrix.length-1][matrix[0].length-1]) {
//            return false;
//        }
//
//        int m = matrix.length;
//        int n = matrix[0].length;
//
//        // find the row
//        int possibleRow = -1;
//        int left = 0, right = m-1;
//
//        while (left <= right) {
//            int mid = left + (right - left)/2;
//
//            if (target == matrix[mid][0] ) {
//                return true;
//            } else if (target < matrix[mid][0]) {
//                right = mid - 1;
//            } else {
//                left = mid + 1;
//                possibleRow = mid;
//            }
//        }
//
//        left = 0;
//        right = n-1;
//        while (left <= right) {
//            int mid = left + (right - left)/2;
//
//            if (target == matrix[possibleRow][mid] ) {
//                return true;
//            } else if (target < matrix[possibleRow][mid]) {
//                right = mid - 1;
//            } else {
//                left = mid + 1;
//            }
//        }
//
//        return false;
//    }
}
