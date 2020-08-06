package edu.codifyme.leetcode.interview.google.arraynstring;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * MEDIUM:
 * https://leetcode.com/problems/3sum
 *
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique
 * triplets in the array which gives the sum of zero.
 *
 * Note:
 * The solution set must not contain duplicate triplets.
 *
 * Example:
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * Approach 1: Two Pointers
 * It's easier to deal with duplicates if the array is sorted. As our BCR is O(n^2), sorting the array would not change the overall time complexity.
 * After sorting the array, we process each value from left to right. For value v, we need to find all pairs whose sum is equal to -v. To do that, we use the Two Sum II: Two Pointers approach for the rest of the array.
 * To make sure the result contains unique triplets, we need to skip duplicate values. It is easy to do because repeating values are next to each other in a sorted array.
 *
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();

        if ( null == nums || nums.length < 3 ) {
            return new LinkedList<List<Integer>>();
        }

        Arrays.sort(nums);

        for (int select = 0; select < nums.length && nums[select] <= 0; select++) {
            if ( select != 0 && nums[select] == nums[select-1] ) {
                continue;
            }
            int start = select + 1;
            int end = nums.length - 1;
            int complement = 0 - nums[select];

            while ( start < end ) {
                int currentSum = nums[start] + nums[end];
                if ( currentSum < complement || (start > select + 1 && nums[start] == nums[start-1])) {
                    start++;
                } else if (currentSum > complement || (end < nums.length-1 && nums[end] == nums[end+1])) {
                    end--;
                } else {
                    result.add(Arrays.asList(nums[select], nums[start], nums[end]));
                    start++;
                    end--;
                }
            }
        }
        return result;
    }
}
