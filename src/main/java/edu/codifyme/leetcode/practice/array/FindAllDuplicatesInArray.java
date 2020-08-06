package edu.codifyme.leetcode.practice.array;

/**
 * MEDIUM: Find All Duplicates in an Array
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/
 *
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 *
 * Find all the elements that appear twice in this array.
 *
 * Could you do it without extra space and in O(n) runtime?
 *
 * Example:
 * Input:
 * [4,3,2,7,8,2,3,1]
 *
 * Output:
 * [2,3]
 *
 * Approach:
 * Change the sign of the visited number index. If we revisit an index which is negative, means number is already
 * visited -> so, add in result list
 */
public class FindAllDuplicatesInArray {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            int elem = Math.abs(nums[i]);
            if ( nums[elem - 1] < 0 ) {
                list.add(elem);
            } else {
                nums[elem - 1] *= -1;
            }
        }

        return list;
    }
}
