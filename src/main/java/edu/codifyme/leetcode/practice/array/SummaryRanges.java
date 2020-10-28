package edu.codifyme.leetcode.practice.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 228. Summary Ranges
 * EASY: https://leetcode.com/problems/summary-ranges/
 *
 * You are given a sorted unique integer array nums.
 *
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of
 * nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not
 * in nums.
 *
 * Each range [a,b] in the list should be output as:
 * "a->b" if a != b
 * "a" if a == b
 *
 * Example 1:
 * Input: nums = [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: The ranges are:
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 *
 * Example 2:
 * Input: nums = [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: The ranges are:
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 *
 * Example 3:
 * Input: nums = []
 * Output: []
 *
 * Example 4:
 * Input: nums = [-1]
 * Output: ["-1"]
 *
 * Example 5:
 * Input: nums = [0]
 * Output: ["0"]
 *
 * Constraints:
 * 0 <= nums.length <= 20
 * -231 <= nums[i] <= 231 - 1
 * All the values of nums are unique.
 *
 * Approach:
 *
 * Intuition:
 * A range covers consecutive elements. If two adjacent elements have difference larger than 11, the two elements does
 * not belong to the same range.
 *
 * Algorithm:
 * To summarize the ranges, we need to know how to separate them. The array is sorted and without duplicates. In such
 * array, two adjacent elements have difference either 1 or larger than 1. If the difference is 1, they should be put in
 * the same range; otherwise, separate ranges.
 *
 * We also need to know the start index of a range so that we can put it in the result list. Thus, we keep two indices,
 * representing the two boundaries of current range. For each new element, we check if it extends the current range. If
 * not, we put the current range into the list.
 *
 * Don't forget to put the last range into the list. One can do this by either a special condition in the loop or
 * putting the last range in to the list after the loop.
 */
public class SummaryRanges {
    // Efficient implementation
    public List<String> summaryRanges(int[] nums) {
        List<String> summary = new ArrayList<>();
        for (int i, j = 0; j < nums.length; ++j){
            i = j;
            // try to extend the range [nums[i], nums[j]]
            while (j + 1 < nums.length && nums[j + 1] == nums[j] + 1)
                ++j;
            // put the range into the list
            if (i == j)
                summary.add(nums[i] + "");
            else
                summary.add(nums[i] + "->" + nums[j]);
        }
        return summary;
    }

    // Alternate implementation
//    public List<String> summaryRanges(int[] nums) {
//        List<String> result = new LinkedList<>();
//
//        if (nums.length == 0) {
//            return result;
//        }
//
//        int firstInPair = nums[0];
//        int secondInPair = nums[0];
//
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i] - secondInPair == 1 ) {
//                secondInPair = nums[i];
//            } else {
//                result.add(getFormat(firstInPair, secondInPair));
//                firstInPair = nums[i];
//                secondInPair = nums[i];
//            }
//        }
//
//        result.add(getFormat(firstInPair, secondInPair));
//
//        return result;
//    }
//
//    String getFormat(int firstInPair, int secondInPair) {
//        if (firstInPair == secondInPair) {
//            return String.valueOf(firstInPair);
//        } else {
//            return firstInPair+"->"+secondInPair;
//        }
//    }
}
