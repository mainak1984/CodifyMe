package edu.codifyme.leetcode.practice.others;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 47. Permutations II
 * MEDIUM: https://leetcode.com/problems/permutations-ii/
 *
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any
 * order.
 *
 * Example 1:
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * Example 2:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Constraints:
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 *
 * Approach:
 * Generate all permutations and skip if the number we are swapping is already seen in that position.
 */
public class PermutationsII {
    List<List<Integer>> result;

    public List<List<Integer>> permuteUnique(int[] nums) {
        result = new ArrayList<>();

        if (null == nums || 0 == nums.length) {
            return result;
        }

        perm(nums, 0);

        return result;
    }

    void perm(int[] nums, int pos) {
        if (pos == nums.length) {
            // System.out.println(Arrays.toString(nums));
            List<Integer> output = Arrays.stream(nums).boxed().collect(Collectors.toList());
            result.add(output);
            return;
        }

        Set<Integer> seen = new HashSet<>();
        for (int i = pos; i < nums.length; i++) {
            if (seen.add(nums[i])) {
                swap(nums, pos, i);
                perm(nums, pos+1);
                swap(nums, i, pos);
            }
        }
    }

    void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}
