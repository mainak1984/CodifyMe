package edu.codifyme.dp;

/**
 * MEDIUM: Partition Equal Subset Sum
 * https://leetcode.com/problems/partition-equal-subset-sum/
 *
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such
 * that the sum of elements in both subsets is equal.
 *
 * Note:
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 *
 * Example 1:
 * Input: [1, 5, 11, 5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 * Example 2:
 * Input: [1, 2, 3, 5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 * Approach:
 * Use 0/1 Knapsack solution; use half the cumulative sum as target
 */
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int targetSum = 0;

        for (int num: nums) {
            targetSum += num;
        }

        if ( targetSum % 2 != 0) {
            return false;
        } else {
            targetSum /= 2;
        }

        boolean[][] dpArr = new boolean[nums.length+1][targetSum+1];

        for (int i = 0; i < nums.length+1; i++) {
            for (int j = 0; j < targetSum+1; j++) {
                if (i == 0 || j == 0) {
                    if (i == 0) {
                        dpArr[i][j] = false;
                        if (j == 0 ) {
                            dpArr[i][j] = true;
                        }
                    }
                } else if (j >= nums[i-1]) {
                    dpArr[i][j] = dpArr[i-1][j] || dpArr[i-1][j - nums[i-1]];
                } else {
                    dpArr[i][j] = dpArr[i-1][j];
                }
            }
        }

        return dpArr[nums.length][targetSum];
    }

//     public boolean canPartition(int[] nums) {
//         int sum = 0;
//         for (int num: nums)
//             sum += num;

//         if (sum % 2 == 1)
//             return false;

//         sum /= 2;
//         boolean[] possible = new boolean[sum + 1];
//         possible[0] = true;
//         for (int num: nums) {
//             for (int j = sum; j >= num; j--)
//                 possible[j] |= possible[j - num];
//         }

//         return possible[sum];
//     }
}
