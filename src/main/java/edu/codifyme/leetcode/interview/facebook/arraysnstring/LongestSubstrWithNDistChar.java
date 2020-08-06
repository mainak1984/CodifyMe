package edu.codifyme.leetcode.interview.facebook.arraysnstring;

import java.util.HashMap;
import java.util.Map;

/**
 * HARD: Longest Substring with At Most K Distinct Characters
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters
 *
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 *
 * Example 1:
 *
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: T is "ece" which its length is 3.
 * Example 2:
 *
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: T is "aa" which its length is 2.
 *
 * Approach:
 * Use 2 pointer approach and template
 */
public class LongestSubstrWithNDistChar {
    public int lengthOfLongestSubstringKDistinct(String s, int distinct) {
        Map<Character, Integer> count = new HashMap<>();

        int left = 0;
        int right = 0;
        int maxLen = Integer.MIN_VALUE;
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
            maxLen = Math.max(maxLen, (right - left));
        }

        return maxLen == Integer.MIN_VALUE? 0: maxLen;
    }
}
