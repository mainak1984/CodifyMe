package edu.codifyme.leetcode.practice.array;

/**
 * 845. Longest Mountain in Array
 * MEDIUM: https://leetcode.com/problems/longest-mountain-in-array/
 *
 * Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:
 *
 * B.length >= 3
 * There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * (Note that B could be any subarray of A, including the entire array A.)
 *
 * Given an array A of integers, return the length of the longest mountain.
 * Return 0 if there is no mountain.
 *
 * Example 1:
 * Input: [2,1,4,7,3,2,5]
 * Output: 5
 * Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
 *
 * Example 2:
 * Input: [2,2,2]
 * Output: 0
 * Explanation: There is no mountain.
 *
 * Note:
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 *
 * Follow up:
 * Can you solve it using only one pass?
 * Can you solve it in O(1) space?
 *
 * Approach:
 * Intuition:
 * Without loss of generality, a mountain can only start after the previous one ends.
 * This is because if it starts before the peak, it will be smaller than a mountain starting previous; and it is
 * impossible to start after the peak.
 *
 * Algorithm:
 * For a starting index base, let's calculate the length of the longest mountain A[base], A[base+1], ..., A[end].
 * If such a mountain existed, the next possible mountain will start at base = end; if it didn't, then either we reached
 * the end, or we have A[base] >= A[base+1] and we can start at base + 1.
 */
public class LongestMountainInArray {
    public int longestMountain(int[] A) {
        int N = A.length;
        int ans = 0, base = 0;
        while (base < N) {
            int end = base;
            // if base is a left-boundary
            if (end + 1 < N && A[end] < A[end + 1]) {
                // set end to the peak of this potential mountain
                while (end + 1 < N && A[end] < A[end + 1]) end++;

                // if end is really a peak..
                if (end + 1 < N && A[end] > A[end + 1]) {
                    // set end to the right-boundary of mountain
                    while (end + 1 < N && A[end] > A[end + 1]) end++;
                    // record candidate answer
                    ans = Math.max(ans, end - base + 1);
                }
            }

            base = Math.max(end, base + 1);
        }

        return ans;
    }
}
