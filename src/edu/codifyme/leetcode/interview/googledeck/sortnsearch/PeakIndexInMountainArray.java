package edu.codifyme.leetcode.interview.googledeck.sortnsearch;

/**
 * EASY:
 * https://leetcode.com/explore/interview/card/google/63/sorting-and-searching-4/3084/
 *
 * Let's call an array A a mountain if the following properties hold:
 * A.length >= 3
 * There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
 * Given an array that is definitely a mountain, return any i such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].
 *
 * Example 1:
 * Input: [0,1,0]
 * Output: 1
 *
 * Example 2:
 * Input: [0,2,1,0]
 * Output: 1
 *
 * Note:
 * 3 <= A.length <= 10000
 * 0 <= A[i] <= 10^6
 * A is a mountain, as defined above.
 */
public class PeakIndexInMountainArray {
    public int peakIndexInMountainArray(int[] A) {
        if (null == A || A.length < 3) {
            return -1;
        }
        return find(A, 0, A.length-1);
    }

    public int find(int[] A, int left, int right) {
        int mid = left + (right - left) / 2;

        if (mid > 0 && mid < A.length - 1 && A[mid - 1] < A[mid] && A[mid] > A[mid + 1]) {
            // Voila; got it
            return mid;
        }

        if ( mid == 0 || (mid > 0 && A[mid-1] < A[mid])) {
            // go right;
            return find(A, mid+1, right);
        } else {
            return find(A, left, mid-1);
        }
    }
}
