package edu.codifyme.leetcode.interview.googledeck.arraynstring;

import java.util.LinkedList;
import java.util.List;

/**
 * MEDIUM:
 * https://leetcode.com/explore/interview/card/google/59/array-and-strings/3055/
 *
 * Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
 *
 * Example:
 * Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
 * Output: ["2", "4->49", "51->74", "76->99"]
 */
public class MissingRanges {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new LinkedList<>();

        if ( null == nums ) {
            return list;
        }
        if ( 0 == nums.length ) {
            list.add(formatOutput(lower, upper));
            return list;
        }

        for (int num: nums) {
            if ( num > lower ) {
                list.add(formatOutput(lower, num-1));
            }
            if ( num == Integer.MAX_VALUE) { // max number
                lower = num;
            } else {
                lower = num+1;
            }
        }

        if (upper >= lower && lower != Integer.MAX_VALUE) {
            list.add(formatOutput(lower, upper));
        }

        return list;
    }

    public String formatOutput(long start, long end) {
        return (start == end)? String.valueOf(start): String.valueOf(start).concat("->").concat(String.valueOf(end));
    }
}
