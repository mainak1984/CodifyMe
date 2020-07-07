package edu.codifyme.geeksforgeeks.array;

/**
 * MEDIUM: Maximum absolute difference between sum of two contiguous sub-arrays
 * https://www.geeksforgeeks.org/maximum-absolute-difference-between-sum-of-two-contiguous-sub-arrays/
 *
 * Given an array of integers, find two non-overlapping contiguous sub-arrays such that the absolute difference between
 * the sum of two sub-arrays is maximum.
 *
 * Example:
 *
 * Input: [-2, -3, 4, -1, -2, 1, 5, -3]
 * Output: 12
 * Two subarrays are [-2, -3] and [4, -1, -2, 1, 5]
 *
 * Input: [2, -1, -2, 1, -4, 2, 8]
 * Output: 16
 * Two subarrays are [-1, -2, 1, -4] and [2, 8]
 * Expected time complexity is O(n).
 *
 * Solution:
 * Move i from 0 to n-1 and have two parts. Find min and max for both left and right sub parts.
 * Run another loop to find the max difference at some i.
 * Imp: for min case, use inverted values of max, no need to calculate again
 *
 * Complexity: O(n)
 */
public class MaxAbsDiffBetweenTwoSubArrays {
    // The function finds two non-overlapping contiguous
    // sub-arrays such that the absolute difference
    // between the sum of two sub-array is maximum.
    static int findMaxAbsDiff(int arr[], int n)
    {
        // create and build an array that stores
        // maximum sums of subarrays that lie in
        // arr[0...i]
        int leftMax[] = new int[n];
        maxLeftSubArraySum(arr, n, leftMax);

        // create and build an array that stores
        // maximum sums of subarrays that lie in
        // arr[i+1...n-1]
        int rightMax[] = new int[n];
        maxRightSubArraySum(arr, n - 1, rightMax);

        // Invert array (change sign) to find minumum
        // sum subarrays.
        int invertArr[] = new int[n];
        for (int i = 0; i < n; i++)
            invertArr[i] = -arr[i];

        // create and build an array that stores
        // minimum sums of subarrays that lie in
        // arr[0...i]
        int leftMin[] = new int[n];
        maxLeftSubArraySum(invertArr, n, leftMin);
        for (int i = 0; i < n; i++)
            leftMin[i] = -leftMin[i];

        // create and build an array that stores
        // minimum sums of subarrays that lie in
        // arr[i+1...n-1]
        int rightMin[] = new int[n];
        maxRightSubArraySum(invertArr, n - 1, rightMin);
        for (int i = 0; i < n; i++)
            rightMin[i] = -rightMin[i];

        int result = -2147483648;
        for (int i = 0; i < n - 1; i++) {

        /* For each index i, take maximum of
        1. abs(max sum subarray that lies in arr[0...i] -
            min sum subarray that lies in arr[i+1...n-1])
        2. abs(min sum subarray that lies in arr[0...i] -
            max sum subarray that lies in arr[i+1...n-1]) */
            int absValue = Math.max(Math.abs(leftMax[i] - rightMin[i + 1]),
                    Math.abs(leftMin[i] - rightMax[i + 1]));
            if (absValue > result)
                result = absValue;
        }

        return result;
    }

    // Find maximum subarray sum for subarray
    // [0..i] using standard Kadane's algorithm.
    // This version of Kadane's Algorithm will
    // work if all numbers are negative.
    static int maxLeftSubArraySum(int a[], int size, int sum[])
    {
        int max_so_far = a[0];
        int curr_max = a[0];
        sum[0] = max_so_far;

        for (int i = 1; i < size; i++) {
            curr_max = Math.max(a[i], curr_max + a[i]);
            max_so_far = Math.max(max_so_far, curr_max);
            sum[i] = max_so_far;
        }

        return max_so_far;
    }

    // Find maximum subarray sum for subarray [i..n]
    // using Kadane's algorithm. This version of Kadane's
    // Algorithm will work if all numbers are negative
    static int maxRightSubArraySum(int a[], int n, int sum[])
    {
        int max_so_far = a[n];
        int curr_max = a[n];
        sum[n] = max_so_far;

        for (int i = n - 1; i >= 0; i--) {
            curr_max = Math.max(a[i], curr_max + a[i]);
            max_so_far = Math.max(max_so_far, curr_max);
            sum[i] = max_so_far;
        }

        return max_so_far;
    }
}
