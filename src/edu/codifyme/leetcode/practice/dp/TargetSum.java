package edu.codifyme.leetcode.practice.dp;

/**
 * MEDIUM:
 * https://leetcode.com/problems/target-sum/
 *
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For
 * each integer, you should choose one from + and - as its new symbol.
 *
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 *
 * Example 1:
 *
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 *
 *
 * Constraints:
 *
 * The length of the given array is positive and will not exceed 20.
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 *
 * Approach:
 * Variation of 0/1 Knapsack problem; Use DP
 *
 *
 */
public class TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        // Find number of subsets having the diff S

        int sum = 0;
        // max diff in array is sum of arr
        for(int num: nums) {
            sum += num;
        }

        if (S > sum || S < -sum || S + sum < 0 || (sum + S) % 2 != 0) {
            return 0;
        }

        sum = (sum + S) / 2;

        int[][] dp = new int[nums.length+1][sum+1];

        for (int j = 0; j < sum+1; j++) {
            dp[0][j] = 0;
        }
        dp[0][0] = 1;


        for (int i = 1; i < nums.length+1; i++) {
            for (int j = 0; j < sum+1; j++) {
                dp[i][j] = dp[i-1][j];
                if (nums[i-1] <= j) {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j - nums[i-1]];
                }
            }
        }

        return dp[nums.length][sum];
    }
}
