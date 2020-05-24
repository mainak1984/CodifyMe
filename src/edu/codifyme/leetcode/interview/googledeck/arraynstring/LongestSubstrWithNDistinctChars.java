package edu.codifyme.leetcode.interview.googledeck.arraynstring;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/explore/interview/card/google/59/array-and-strings/3054/
 *
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 *
 * Example 1:
 *
 * Input: "eceba"
 * Output: 3
 * Explanation: t is "ece" which its length is 3.
 * Example 2:
 *
 * Input: "ccaabbb"
 * Output: 5
 * Explanation: t is "aabbb" which its length is 5.
 *
 * Approach 1: Sliding Window
 * To solve the problem in one pass let's use here sliding window approach with two set pointers left and right serving
 * as the window boundaries.
 * The idea is to set both pointers in the position 0 and then move right pointer to the right while the window contains
 * not more than two distinct characters. If at some point we've got 3 distinct characters, let's move left pointer to
 * keep not more than 2 distinct characters in the window.
 */
public class LongestSubstrWithNDistinctChars {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> count = new HashMap<>();
        int distinct = 2;

        int left = 0;
        int right = 0;
        int maxLen = Integer.MIN_VALUE;
        // int maxStart = 0;
        int unique = 0;

        while ( right < s.length() ) {
            if (!count.containsKey(s.charAt(right))) {
                unique++;
            }
            count.put(s.charAt(right), count.getOrDefault(s.charAt(right), 0) + 1);
            right++;

            while ( unique > distinct ) { // shrink from left to make the count of 2 unique key
                count.put(s.charAt(left), count.getOrDefault(s.charAt(left), 0) - 1);

                if (count.get(s.charAt(left)) == 0) {
                    count.remove(s.charAt(left));
                    unique--;
                }
                left++;
            }

            // if (maxLen < (right - left)) {
            //     maxLen = right - left;
            //     maxStart = left;
            // }
            maxLen = Math.max(maxLen, (right - left));
        }

        return maxLen == Integer.MIN_VALUE? 0: maxLen;
    }
}
