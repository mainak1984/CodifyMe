package edu.codifyme.leetcode.practice.searchnsort;

/**
 * 1539. Kth Missing Positive Number
 * EASY: https://leetcode.com/problems/kth-missing-positive-number/
 *
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 * Find the kth positive integer that is missing from this array.
 *
 * Example 1:
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
 *
 * Example 2:
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 *
 * Constraints:
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 1000
 * 1 <= k <= 1000
 * arr[i] < arr[j] for 1 <= i < j <= arr.length
 *
 * Approach:
 * Approach 1: Brute Force, O(N) time
 * The first idea is to solve the problem in a linear time by parsing all array elements. It's easy to say how many
 * positive numbers are missing between two array elements: arr[i + 1] - arr[i]-1, and we could use it to solve the problem.
 *
 * Approach 2: Binary Search, O(logN) time
 * We need a way to check on how many positive integers are missing before the given array element to use binary search.
 * To do that, let's compare the input array [2, 3, 4, 7, 11] with an array with no missing integers: [1, 2, 3, 4, 5].
 * The number of missing integers is a simple difference between the corresponding elements of these two arrays:
 * The number of positive integers which are missing before the arr[idx] is equal to arr[idx] - idx - 1.
 *
 * Now we have everything to proceed with the classical binary search algorithm:
 * - Choose the pivot index in the middle of the array.
 * - If the number of positive integers which are missing before arr[pivot] is less than k - continue to search on the
 * right side of the array.
 * - Otherwise, continue to search on the left side.
 */
public class KthMissingPositiveNumber {
    // Approach 2: Binary Search, \mathcal{O}(\log N)O(logN) time
    public int findKthPositive(int[] arr, int k) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int pivot = left + (right - left) / 2;
            // If number of positive integers which are missing before arr[pivot] is less than k --> continue to search
            // on the right.
            if (arr[pivot] - pivot - 1 < k) {
                left = pivot + 1;
                // Otherwise, go left.
            } else {
                right = pivot - 1;
            }
        }

        // At the end of the loop, left = right + 1, and the kth missing is in-between arr[right] and arr[left].
        // The number of integers missing before arr[right] is arr[right] - right - 1 -->
        // the number to return is srr[right] + k - (arr[right] - right - 1) = k + left
        return left + k;
    }

    // Approach 1: Brute Force, O(N) time
    public int findKthPositive1(int[] arr, int k) {
        int result = 1;
        int i = 0;

        while (k != 0 && i < arr.length) {
            if (result != arr[i]) {
                k--;
            } else {
                i++;
            }

            result++;
        }

        return (k > 0)? result-1 + k: result-1;
    }
}
