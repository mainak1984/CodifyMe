package edu.codifyme.leetcode.interview.facebook.arraysnstring;

/**
 * 238. Product of Array Except Self
 * MEDIUM: https://leetcode.com/problems/product-of-array-except-self/
 * Editor's choice: Frequently asked in a Facebook online assessment.
 *
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of
 * all the elements of nums except nums[i].
 *
 * Example:
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole
 * array) fits in a 32 bit integer.
 *
 * Note: Please solve it without division and in O(n).
 *
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of
 * space complexity analysis.)
 *
 * Approach:
 * Store leftSide product and rightSide product for each location in nums and result. Utilize it to update result.
 *
 * Initialize the empty answer array where for a given index i, answer[i] would contain the product of all the numbers
 * to the left of i.
 * We construct the answer array the same way we constructed the L array in the previous approach. These two algorithms
 * are exactly the same except that we are trying to save up on space.
 * The only change in this approach is that we don't explicitly build the R array from before. Instead, we simply use a
 * variable to keep track of the running product of elements to the right and we keep updating the answer array by doing
 * answer[i] = answer[i] * R. For a given index i, answer[i] contains the product of all the elements to the left and R
 * would contain product of all the elements to the right. We then update R as R = R * nums[i]
 */
public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
}
