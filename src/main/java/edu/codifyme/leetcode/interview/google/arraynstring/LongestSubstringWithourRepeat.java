package edu.codifyme.leetcode.interview.google.arraynstring;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. Longest Substring Without Repeating Characters
 * MEDIUM: https://leetcode.com/problems/longest-substring-without-repeating-characters
 *
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * Approach:
 * We use HashSet to store the characters in current window [i, j)[i,j) (j = ij=i initially). Then we slide the index jj
 * to the right. If it is not in the HashSet, we slide jj further. Doing so until s[j] is already in the HashSet. At this
 * point, we found the maximum size of substrings without duplicate characters start with index ii. If we do this for all
 * ii, we get our answer.
 */
public class LongestSubstringWithourRepeat {

    public int lengthOfLongestSubstring(String s) {
        // Map of character and their last occurance
        Map<Character, Integer> uniqChar = new HashMap<>();
        int removeIdx = 0;
        int max = 0;

        if ( null == s || 0 == s.length() ) {
            return max;
        }
        // for all chars, add the char, check count of map, if non unique, process start removing until this char remains
        char[] input = s.toCharArray();
        for (int loop = 0; loop < input.length; loop++) {
            if ( uniqChar.containsKey(input[loop]) ) {
                max = (loop - removeIdx > max)? loop - removeIdx : max;

                int oldIndex = uniqChar.get(input[loop]);

                while ( removeIdx <= oldIndex ) {
                    uniqChar.remove(input[removeIdx]);
                    removeIdx++;
                }
            }
            uniqChar.put(input[loop], loop);
        }

        max = (input.length - removeIdx > max)? input.length - removeIdx : max;

        return max;
    }

//    public int lengthOfLongestSubstring(String s) {
//        int n = s.length(), ans = 0;
//        Map<Character, Integer> map = new HashMap<>(); // current index of character
//        // try to extend the range [i, j]
//        for (int j = 0, i = 0; j < n; j++) {
//            if (map.containsKey(s.charAt(j))) {
//                i = Math.max(map.get(s.charAt(j)), i);
//            }
//            ans = Math.max(ans, j - i + 1);
//            map.put(s.charAt(j), j + 1);
//        }
//        return ans;
//    }
}
