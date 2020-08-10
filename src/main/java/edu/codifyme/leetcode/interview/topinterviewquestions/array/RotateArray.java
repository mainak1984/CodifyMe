package edu.codifyme.leetcode.interview.topinterviewquestions.array;

/**
 * 189. Rotate Array
 * EASY: https://leetcode.com/problems/rotate-array/
 *
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 *  Example 1:
 *
 * Input: [1,2,3,4,5,6,7] and k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 *
 * Example 2:
 *
 * Input: [-1,-100,3,99] and k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 * Note:
 *
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 */
public class RotateArray {
    public void rotate(int[] nums, int k) {
        if ( nums.length < 1 ) {
            return;
        }

        int baseStarting = 0;
        int loopCount = 0;

        while (loopCount < nums.length) {

            int prevIndex = baseStarting;
            int nextIndex = (baseStarting + k) % nums.length;
            int prevValue = nums[baseStarting];
            int temp = nums[nextIndex];

            while (nextIndex != baseStarting) {
                nums[nextIndex] = prevValue;
                prevValue = temp;
                prevIndex = nextIndex;
                nextIndex = (prevIndex + k) % nums.length;
                temp = nums[nextIndex];
                loopCount += 1;
            }

            nums[nextIndex] = prevValue;
            baseStarting += 1;
            loopCount += 1;
        }
    }

    /** BEST Solution
    public void rotate(int[] nums, int k) {
        k%=nums.length;


        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
     **/
}
