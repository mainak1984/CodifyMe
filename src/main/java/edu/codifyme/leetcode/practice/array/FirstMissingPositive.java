package edu.codifyme.leetcode.practice.array;

/**
 * 41. First Missing Positive
 * HARD: https://leetcode.com/problems/first-missing-positive/
 *
 * Given an unsorted integer array, find the smallest missing positive integer.
 *
 * Example 1:
 * Input: [1,2,0]
 * Output: 3
 *
 * Example 2:
 * Input: [3,4,-1,1]
 * Output: 2
 *
 * Example 3:
 * Input: [7,8,9,11,12]
 * Output: 1
 *
 * Follow up:
 * Your algorithm should run in O(n) time and uses constant extra space.
 *
 * Appraoch:
 * Data clean up:
 * First of all let's get rid of negative numbers and zeros since there is no need of them. One could get rid of all
 * numbers larger than n as well, since the first missing positive is for sure smaller or equal to n + 1.
 *
 * Algorithm:
 * - Check if 1 is present in the array. If not, you're done and 1 is the answer.
 * - If nums = [1], the answer is 2.
 * - Replace negative numbers, zeros, and numbers larger than n by 1s.
 * - Walk along the array. Change the sign of a-th element if you meet number a. Be careful with duplicates : do sign
 *    change only once. Use index 0 to save an information about presence of number n since index n is not available.
 * - Walk again along the array. Return the index of the first positive element.
 * - If nums[0] > 0 return n.
 * - If on the previous step you didn't find the positive element in nums, that means that the answer is n + 1.
 */
public class FirstMissingPositive {
    // Approach 1: Mark 0 during cleanup and add 2*max for marking visited
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > nums.length) {
                nums[i] = 0;
            }
        }

        int max = nums.length + 1;
        int multiplier = 2*max;

        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            if (curr == 0 || curr == multiplier) {
                continue;
            }
            if (curr > max) {
                curr -= multiplier;
            }
            if (nums[curr-1] < multiplier) {
                nums[curr-1] += multiplier;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < max) {
                return i+1;
            }
        }
        return nums.length+1;
    }

    // Alternate approach: set 1 during data clean up and mark index with negative sign
//    public int firstMissingPositive(int[] nums) {
//        int n = nums.length;
//
//        // Base case.
//        int contains = 0;
//        for (int i = 0; i < n; i++)
//            if (nums[i] == 1) {
//                contains++;
//                break;
//            }
//
//        if (contains == 0)
//            return 1;
//
//        // nums = [1]
//        if (n == 1)
//            return 2;
//
//        // Replace negative numbers, zeros,
//        // and numbers larger than n by 1s.
//        // After this convertion nums will contain
//        // only positive numbers.
//        for (int i = 0; i < n; i++)
//            if ((nums[i] <= 0) || (nums[i] > n))
//                nums[i] = 1;
//
//        // Use index as a hash key and number sign as a presence detector.
//        // For example, if nums[1] is negative that means that number `1`
//        // is present in the array.
//        // If nums[2] is positive - number 2 is missing.
//        for (int i = 0; i < n; i++) {
//            int a = Math.abs(nums[i]);
//            // If you meet number a in the array - change the sign of a-th element.
//            // Be careful with duplicates : do it only once.
//            if (a == n)
//                nums[0] = - Math.abs(nums[0]);
//            else
//                nums[a] = - Math.abs(nums[a]);
//        }
//
//        // Now the index of the first positive number
//        // is equal to first missing positive.
//        for (int i = 1; i < n; i++) {
//            if (nums[i] > 0)
//                return i;
//        }
//
//        if (nums[0] > 0)
//            return n;
//
//        return n + 1;
//    }
}
