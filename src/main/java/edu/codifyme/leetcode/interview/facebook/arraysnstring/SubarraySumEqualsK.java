package edu.codifyme.leetcode.interview.facebook.arraysnstring;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. Subarray Sum Equals K
 * MEDIUM: https://leetcode.com/problems/subarray-sum-equals-k/
 *
 * Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 * Constraints:
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 *
 * Approach:
 * The idea behind this approach is as follows: If the cumulative sum(represented by sum[i] for sum upto ith index) upto
 * two indices is the same, the sum of the elements lying in between those indices is zero. Extending the same thought
 * further, if the cumulative sum upto two indices, say i and j is at a difference of k i.e. if sum[i] - sum[j] = k, the
 * sum of elements lying between indices i and j is k.
 *
 * Based on these thoughts, we make use of a hashmap map which is used to store the cumulative sum upto all the indices
 * possible along with the number of times the same sum occurs. We store the data in the form: (sum_i, no. of occurences
 * of sum_i). We traverse over the array nums and keep on finding the cumulative sum. Every time we encounter a new sum,
 * we make a new entry in the hashmap corresponding to that sum. If the same sum occurs again, we increment the count
 * corresponding to that sum in the hashmap. Further, for every sum encountered, we also determine the number of times
 * the sum sum-k has occured already, since it will determine the number of times a subarray with sum kk has occured
 * upto the current index. We increment the count by the same amount.
 *
 * After the complete array has been traversed, the count gives the required result.
 */
public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int result = 0;
        int sum = 0;

        for (int num: nums) {
            // freq.put(sum, freq.getOrDefault(sum, 0)+1);
            freq.compute(sum, (key, value) -> value == null ? 1 : value+1);;
            sum += num;

            if (freq.containsKey(sum - k)) {
                result += freq.get(sum - k);
            }
        }

        return result;
    }
}
