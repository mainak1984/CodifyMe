package edu.codifyme.leetcode.practice.array;

/**
 * 283. Move Zeroes
 * EASY: https://leetcode.com/problems/move-zeroes/
 *
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the
 * non-zero elements.
 *
 * Example:
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Note:
 *
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 *
 * Approach:
 * Two pointer: have one pointer for last nonzero and replace it with 0
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int left = 0;

        if (nums == null || nums.length == 0) {
            return;
        }

        while (left < nums.length && nums[left] != 0) {
            left++;
        }

        for (int right = left+1; right < nums.length; right++) {
            if (nums[right] == 0) {
                continue;
            }
            nums[left++] = nums[right];
            nums[right] = 0;
        }
    }
}
