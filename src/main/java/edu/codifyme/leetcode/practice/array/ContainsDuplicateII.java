package edu.codifyme.leetcode.practice.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 219. Contains Duplicate II
 * EASY: https://leetcode.com/problems/contains-duplicate-ii/
 *
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array
 * such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 * Example 2:
 *
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 * Example 3:
 *
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 *
 * Solution:
 * Use hashmap or hash set to check if the same number is present at k distance.
 */
public class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();

        for(int i = 0; i < nums.length; i++){
            if(i > k)
                set.remove(nums[i-k-1]);

            if(!set.add(nums[i]))
                return true;
        }
        return false;
    }
}
