package edu.codifyme.leetcode.interview.topinterviewquestions.array;

/**
 * 154. Find Minimum in Rotated Sorted Array II
 * HARD: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 *
 * Find the minimum element.
 *
 * The array may contain duplicates.
 *
 * Example 1:
 *
 * Input: [1,3,5]
 * Output: 1
 * Example 2:
 *
 * Input: [2,2,2,0,1]
 * Output: 0
 * Note:
 *
 * This is a follow up problem to Find Minimum in Rotated Sorted Array.
 * Would allow duplicates affect the run-time complexity? How and why?
 */
// NOTE: Same code works for without duplicate also
public class FindMinInRotatedArray {
    public int findMin(int[] nums) {
        return findMinNum(nums, 0, nums.length-1, Integer.MIN_VALUE);
    }

    private int findMinNum(int[] nums, int left, int right, int target) {

        if (left > right) {
            if ( right < 0) {
                return nums[left];
            } if (left == nums.length ) {
                return nums[right];
            } else {
                return nums[left] < nums[right]? nums[left]: nums[right];
            }
        }

        int mid = (left + right)/2;

        if (target == nums[mid]) {
            return nums[mid];
        } else {
            if ( nums[right] >= nums[mid] ) {
                return Math.min(findMinNum(nums, left, mid-1, Integer.MIN_VALUE), nums[mid]);
            } else {
                return findMinNum(nums, mid+1, right, Integer.MIN_VALUE);
            }
        }
    }
}
