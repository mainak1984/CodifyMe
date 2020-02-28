package edu.codifyme.leetcode.interview.list1;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 * EASY: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class FirstAndLastOfSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] arr = new int[2];
        arr[0] = Integer.MAX_VALUE;
        arr[1] = Integer.MIN_VALUE;
        recur(nums, 0, nums.length-1, target, arr, 0, true);
        recur(nums, 0, nums.length-1, target, arr, 1, false);

        return arr;
    }

    private void recur(int[] nums, int left, int right, int target, int[] arr, int indexPos, boolean isLeft) {
        int mid = ((left + right) / 2);

        if ( left > right ) {
            if ( isLeft && arr[indexPos] == Integer.MAX_VALUE ) {
                arr[indexPos] = -1;
            }
            if ( !isLeft && arr[indexPos] == Integer.MIN_VALUE ) {
                arr[indexPos] = -1;
            }
            return;
        }

        if ( nums[mid] == target ) {
            if ( isLeft && arr[indexPos] > mid ) {
                arr[indexPos] = mid;
            }
            if ( !isLeft && arr[indexPos] < mid ) {
                arr[indexPos] = mid;
            }
        }

        if ( target < nums[mid] ) {
            // search left
            recur(nums, left, mid-1, target, arr, indexPos, isLeft);
        } else if ( target > nums[mid] ) {
            // search right
            recur(nums, mid+1, right, target, arr, indexPos, isLeft);
        } else {
            if (isLeft) {
                // search left
                recur(nums, left, mid-1, target, arr, indexPos, isLeft);
            } else {
                // search right
                recur(nums, mid+1, right, target, arr, indexPos, isLeft);
            }
        }
    }
}
