package edu.codifyme.leetcode.practice.array;

/**
 * 213. House Robber II
 * MEDIUM: https://leetcode.com/problems/house-robber-ii/
 *
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed
 * . All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two
 * adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers nums representing the amount of money of each house, return the maximum amount
 * of money you can rob tonight without alerting the police.
 *
 * Example 1:
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 *
 * Example 2:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 *
 * Example 3:
 * Input: nums = [0]
 * Output: 0
 *
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 *
 * Approach:
 *
 */
public class HouseRobberII {
    public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        int max1 = rob_simple(nums, 0, nums.length - 2);
        int max2 = rob_simple(nums, 1, nums.length - 1);

        return Math.max(max1, max2);
    }

    public int rob_simple(int[] nums, int start, int end) {
        int t1 = 0;
        int t2 = 0;

        for (int i = start; i <= end; i++) {
            int temp = t1;
            int current = nums[i];
            t1 = Math.max(current + t2, t1);
            t2 = temp;
        }

        return t1;
    }
}
