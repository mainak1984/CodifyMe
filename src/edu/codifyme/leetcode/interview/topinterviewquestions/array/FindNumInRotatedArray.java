package edu.codifyme.leetcode.interview.topinterviewquestions.array;

/**
 * 33. Search in Rotated Sorted Array
 * MEDIUM: https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * Example 1:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 */
public class FindNumInRotatedArray {

    public int search(int[] nums, int target) {
        // call recur function with start and end
        return search(nums, 0, nums.length-1, target);
    }

    private int search(int[] nums, int left, int right, int target) {
        int mid = (left + right)/2;

        // System.out.println("left "+left+", right "+right);

        // breaking condition
        if (left > right) {
            return -1;
        }

        // check with mid; return if nums[mid] == target
        if ( target == nums[mid] ) {
            return mid;
        } else if ( target < nums[mid] ) {
            if ( nums[right] > nums[mid] || target >= nums[left] ) {
                return search(nums, left, mid-1, target);
            } else {
                return search(nums, mid + 1, right, target);
            }
        } else {
            if ( nums[left] < nums[mid] || target <= nums[right] ) {
                return search(nums, mid + 1, right, target);
            } else {
                return search(nums, left, mid-1, target);
            }
        }
    }
}
