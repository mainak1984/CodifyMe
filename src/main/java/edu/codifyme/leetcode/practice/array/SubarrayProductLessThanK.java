package edu.codifyme.leetcode.practice.array;

/**
 * 713. Subarray Product Less Than K
 * MEDIUM: https://leetcode.com/problems/subarray-product-less-than-k/
 *
 * Your are given an array of positive integers nums.
 *
 * Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less
 * than k.
 *
 * Example 1:
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6],
 * [5, 2, 6].
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 *
 * Note:
 * 0 < nums.length <= 50000.
 * 0 < nums[i] < 1000.
 * 0 <= k < 10^6.
 *
 * Approach:
 * Our loop invariant is that left is the smallest value so that the product in the window prod = nums[left] *
 * nums[left + 1] * ... * nums[right] is less than k.
 *
 * For every right, we update left and prod to maintain this invariant. Then, the number of intervals with subarray
 * product less than k and with right-most coordinate right, is right - left + 1. We'll count all of these for each
 * value of right.
 */
public class SubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int left = 0, right = 0, mul = 1, count = 0;

        while (left < nums.length && right < nums.length) {
            if (mul * nums[right] < k) {
                mul *= nums[right];
                count += (right - left + 1);
                right++;
            } else {
                left++;
                if (left > right) {
                    right = left;
                } else {
                    mul /= nums[left-1];
                }
            }
        }

        return count;
    }

//     public int numSubarrayProductLessThanK(int[] nums, int k) {
//         int res = 0;
//         int start = 0;
//         int prod = 1;

//         for (int i = 0; i < nums.length; i++) {
//             if (nums[i] >= k) {
//                 start = i + 1;
//                 prod = 1;
//             } else {
//                 prod *= nums[i];
//                 while (prod >= k ) {
//                     prod /= nums[start];
//                     start++;
//                 }
//                 res += i - start + 1;
//             }
//         }
//         return res;
//     }

    // public int numSubarrayProductLessThanK(int[] nums, int k) {
    //     int n = nums.length;
    //     long p = 1l;
    //     int i = 0;
    //     int j = 0;
    //     int total = 0;
    //     while(j < n){
    //         p *= nums[j];
    //         while(i <= j&&p >= k){
    //             p /= nums[i];
    //             i++;
    //         }
    //         total += (j - i + 1);
    //         j++;
    //     }
    //     return total;
    // }
}
