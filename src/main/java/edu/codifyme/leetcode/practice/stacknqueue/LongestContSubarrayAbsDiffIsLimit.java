package edu.codifyme.leetcode.practice.stacknqueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

/**
 * MEDIUM: Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
 * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
 *
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the
 * absolute difference between any two elements of this subarray is less than or equal to limit.
 *
 * Example 1:
 * Input: nums = [8,2,4,7], limit = 4
 * Output: 2
 * Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4.
 * [8,2] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
 * [2] with maximum absolute diff |2-2| = 0 <= 4.
 * [2,4] with maximum absolute diff |2-4| = 2 <= 4.
 * [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
 * [4] with maximum absolute diff |4-4| = 0 <= 4.
 * [4,7] with maximum absolute diff |4-7| = 3 <= 4.
 * [7] with maximum absolute diff |7-7| = 0 <= 4.
 * Therefore, the size of the longest subarray is 2.
 *
 * Example 2:
 * Input: nums = [10,1,2,4,7,2], limit = 5
 * Output: 4
 * Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
 *
 * Example 3:
 * Input: nums = [4,2,2,2,4,4,2,2], limit = 0
 * Output: 3
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= limit <= 10^9
 *
 * Approach:
 * Pattern included
 * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/discuss/609771/JavaC%2B%2BPython-Deques-O(N)
 * Video explanation: https://www.youtube.com/watch?v=LDFZm4iB7tA&feature=youtu.be
 *
 * Use two pointer approach.
 * > Use TreeMap as the datastructure for storing the active section - O(nlogn)
 * > Use two Deques to store Min and Max for O(N)
 */
public class LongestContSubarrayAbsDiffIsLimit {
    public int longestSubarray(int[] A, int limit) {
        Deque<Integer> maxd = new ArrayDeque<>();
        Deque<Integer> mind = new ArrayDeque<>();
        int i = 0, j;
        for (j = 0; j < A.length; ++j) {
            while (!maxd.isEmpty() && A[j] > maxd.peekLast()) maxd.pollLast();
            while (!mind.isEmpty() && A[j] < mind.peekLast()) mind.pollLast();
            maxd.add(A[j]);
            mind.add(A[j]);
            if (maxd.peek() - mind.peek() > limit) {
                if (maxd.peek() == A[i]) maxd.poll();
                if (mind.peek() == A[i]) mind.poll();
                ++i;
            }
        }
        return j - i;
    }

    // TreeMap
//    public int longestSubarray(int[] nums, int limit) {
//        int left = 0;
//        int right = 0;
//        int maxLen = 0;
//        TreeMap<Integer, Integer> map = new TreeMap<>();
//
//        while (right < nums.length) {
//            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
//
//            if (map.lastKey() - map.firstKey() > limit) {
//                map.put(nums[left], map.getOrDefault(nums[left], 0) - 1);
//                if (map.get(nums[left]) == 0) {
//                    map.remove(nums[left]);
//                }
//                left++;
//            }
//            right++;
//            maxLen = maxLen > (right - left)? maxLen: (right - left);
//        }
//
//        return maxLen;
//    }
}
