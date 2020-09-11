package edu.codifyme.leetcode.interview.google.dp;

/**
 * 152. Maximum Product Subarray
 * MEDIUM: https://leetcode.com/problems/maximum-product-subarray
 *
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has
 * the largest product.
 *
 * Example 1:
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 *
 * Example 2:
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 * Approach 2: Dynamic Programming
 * While going through numbers in nums, we will have to keep track of the maximum product up to that number (we will
 * call max_so_far) and minimum product up to that number (we will call min_so_far). The reason behind keeping track of
 * max_so_far is to keep track of the accumulated product of positive numbers. The reason behind keeping track of
 * min_so_far is to properly handle negative numbers.
 *
 * max_so_far is updated by taking the maximum value among:
 *
 * Current number.
 * This value will be picked if the accumulated product has been really bad (even compared to the current number). This
 * can happen when the current number has a preceding zero (e.g. [0,4]) or is preceded by a single negative number (e.g.
 * [-3,5]).
 * Product of last max_so_far and current number.
 * This value will be picked if the accumulated product has been steadily increasing (all positive numbers).
 * Product of last min_so_far and current number.
 * This value will be picked if the current number is a negative number and the combo chain has been disrupted by a
 * single negative number before (In a sense, this value is like an antidote to an already poisoned combo chain).
 * min_so_far is updated in using the same three numbers except that we are taking minimum among the above three numbers.
 */
public class MaximumProductSubarray {
    /**
     * Have 2 internal state, max/min at this point
     * depending on next value, max or min into A[i] can provide max value
     */
    public int maxProduct(int[] A) {
        if (A.length == 0) {
            return 0;
        }

        int maxherepre = A[0];
        int minherepre = A[0];
        int maxsofar = A[0];
        int maxhere, minhere;

        for (int i = 1; i < A.length; i++) {
            maxhere = Math.max(Math.max(maxherepre * A[i], minherepre * A[i]), A[i]);
            minhere = Math.min(Math.min(maxherepre * A[i], minherepre * A[i]), A[i]);
            maxsofar = Math.max(maxhere, maxsofar);
            maxherepre = maxhere;
            minherepre = minhere;
        }
        return maxsofar;
    }
}
