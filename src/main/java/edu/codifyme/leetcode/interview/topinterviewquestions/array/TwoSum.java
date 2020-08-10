package edu.codifyme.leetcode.interview.topinterviewquestions.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Two Sum
 * EASY: https://leetcode.com/problems/two-sum/
 *
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 *
 * Given nums = [2, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
    //         Arrays.sort(nums);

    //         int left = 0;
    //         int right = nums.length - 1;

    //         while (left < right) {
    //             if ( nums[left] + nums[right] < target ) {
    //                 left += 1;
    //             } else if ( nums[left] + nums[right] > target ) {
    //                 right -= 1;
    //             } else {
    //                 // found the match
    //                 break;
    //             }
    //         }

    //         if ( left < right ) {
    //             int[] ans = new int[2];
    //             ans[0] = left;
    //             ans[1] = right;
    //             return ans;
    //         } else {
    //             return new int[0];
    //         }
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++ ) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++ ) {
            int loc = map.getOrDefault((target - nums[i]), 0);
            if ( loc != 0 && loc != i ) {
                int[] ans = new int[2];
                ans[0] = i;
                ans[1] = loc;
                return ans;
            }
        }

        return new int[0];
    }
}
