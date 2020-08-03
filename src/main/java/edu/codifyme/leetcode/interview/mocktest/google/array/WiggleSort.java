package edu.codifyme.leetcode.interview.mocktest.google.array;

import java.util.Arrays;

/**
 * MEDIUM: Wiggle Sort
 * https://leetcode.com/problems/wiggle-sort/
 *
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *
 * Example:
 * Input: nums = [3,5,2,1,6,4]
 * Output: One possible answer is [3,5,1,6,2,4]
 */
public class WiggleSort {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);

        int i = 1;
        while (i+1 < nums.length) {
            swap(nums, i, i+1);
            i += 2;
        }
    }

    void swap(int[] num, int pos1, int pos2) {
        int temp = num[pos1];
        num[pos1] = num[pos2];
        num[pos2] = temp;
    }
}
