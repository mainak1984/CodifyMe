package edu.codifyme.leetcode.interview.google.dp;

/**
 * HARD: Split Array Largest Sum
 * Editor's choice: Frequently asked in Google Phone Interview
 * https://leetcode.com/problems/split-array-largest-sum
 *
 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty
 * continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
 *
 * Note:
 * If n is the length of array, assume the following constraints are satisfied:
 *
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 *
 * Examples:
 * Input:
 * nums = [7,2,5,10,8]
 * m = 2
 *
 * Output:
 * 18
 *
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 *
 *
 * Approach: DP
 * Let's define f[i][j] to be the minimum largest subarray sum for splitting nums[0..i] into j parts.
 * Consider the jth subarray. We can split the array from a smaller index k to i to form it. Thus f[i][j] can be derived
 * from max(f[k][j - 1], nums[k + 1] + ... + nums[i]). For all valid index k, f[i][j] should choose the minimum value of
 * the above formula.
 * The final answer should be f[n][m], where n is the size of the array.
 * For corner situations, all the invalid f[i][j] should be assigned with INFINITY, and f[0][0] should be initialized with 0.
 *
 * Approach: Binary Search
 * Set the search range between min=(largest single value) and max=(sum of all values).
 * The min starts there because we're looking for the sum of the largest group in the final set of groups. And no matter
 * what groups you create, the largest value has to be in it, so the largest group can't be smaller than that. (This
 * assumes no negative numbers.)
 *
 * Calculate the midpoint between min and max. This midpoint is the group size we're going to try out to see how well it
 * performs.
 *
 * Split the nums list into groups such that no group has a value larger than the chosen midpoint.
 * Note that we may end up with too many or too few groups. That's fine.
 *
 * Compare the number of groups we created against the target m. If we created too many groups, we know the final answer
 * must be between mid+1 and max. That's because we need fewer groups and the way to achieve fewer groups is to increase
 * the allowed maximum sum in each group.
 *
 * On the other hand, if the number of groups is too small, we know the final answer is between min and mid-1 because we
 * need to increase the number of groups which means the target sum is something smaller than the one we used. This is
 * actually also a possible answer assuming m is valid because you can always take any group and split it up to make more
 * groups, so the mid value you targeted is at worst, higher than the real value.
 *
 * On the third hand, if the number of groups is just right, we have a possible answer, so remember that answer. However,
 * we should keep searching just in case there is a better answer. We're ultimately looking for smaller maximum sums, so
 * the potentially better answer is between min and mid-1.
 *
 * Repeat the process until there is nothing else to search. Then use the minimum value we found during the above process.
 */
public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        long l = 0;
        long r = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            r += nums[i];
            if (l < nums[i]) {
                l = nums[i];
            }
        }
        long ans = r;
        while (l <= r) {
            long mid = (l + r) >> 1;
            long sum = 0;
            int cnt = 1;
            for (int i = 0; i < n; i++) {
                if (sum + nums[i] > mid) {
                    cnt ++;
                    sum = nums[i];
                } else {
                    sum += nums[i];
                }
            }
            if (cnt <= m) {
                ans = Math.min(ans, mid);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int)ans;
    }


    //Approach: DP
//    public int splitArray(int[] nums, int m) {
//        int n = nums.length;
//        int[][] f = new int[n + 1][m + 1];
//        int[] sub = new int[n + 1];
//        for (int i = 0; i <= n; i++) {
//            for (int j = 0; j <= m; j++) {
//                f[i][j] = Integer.MAX_VALUE;
//            }
//        }
//        for (int i = 0; i < n; i++) {
//            sub[i + 1] = sub[i] + nums[i];
//        }
//        f[0][0] = 0;
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= m; j++) {
//                for (int k = 0; k < i; k++) {
//                    f[i][j] = Math.min(f[i][j], Math.max(f[k][j - 1], sub[i] - sub[k]));
//                }
//            }
//        }
//        return f[n][m];
//    }
}
