package edu.codifyme.leetcode.practice.array;

import java.util.HashSet;

/**
 * HARD: Longest Consecutive Sequence
 * https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * Your algorithm should run in O(n) complexity.
 *
 * Example:
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 * Solution:
 * Put all the element in a set
 * Start traversing thru the array, and look for that element’s left and right until not found
 * Start increasing the count while elements are found and remove the element to do double count
 * Keep track of the max element
 *
 * Approach 2:
 * We can also project the arrays to a new array with length to be the largest element in the array. Then iterate over
 * the array and get the longest consecutive sequence. If the largest number is very large, then the time complexity
 * would be bad.
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int num: nums) set.add(num);

        int result = 0;

        for(int num: nums) {
            int count = 1;

            int down = num-1;
            while(set.contains(down)) {
                set.remove(down);
                down--;
                count++;
            }

            int up = num+1;
            while(set.contains(up)) {
                set.remove(up);
                up++;
                count++;
            }

            result = Math.max(result, count);
        }

        return result;
    }
}
