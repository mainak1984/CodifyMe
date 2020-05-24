package edu.codifyme.leetcode.interview.googledeck.arraynstring;

import java.util.HashMap;

/**
 * HARD:
 * https://leetcode.com/explore/interview/card/google/59/array-and-strings/345/
 *
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 *
 * Algorithm :
 * We start with two pointers, leftleft and rightright initially pointing to the first element of the string SS.
 * We use the rightright pointer to expand the window until we get a desirable window i.e. a window that contains all of the characters of TT.
 * Once we have a window with all the characters, we can move the left pointer ahead one by one. If the window is still a desirable one we keep on updating the minimum window size.
 * If the window is not desirable any more, we repeat step \; 2step2 onwards.
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> countMap = new HashMap<>();

        for (char ch: s.toCharArray()) {
            countMap.put(ch, 0);
        }

        for (char ch: t.toCharArray()) {
            if (!countMap.containsKey(ch)) {
                return "";
            } else {
                countMap.put(ch, countMap.getOrDefault(ch, 0) + 1);
            }
        }

        int left = 0;
        int right = 0;
        int found = t.length();
        int minLength = Integer.MAX_VALUE;
        int minStart = 0;

        while ( right < s.length() ) {
            char rch = s.charAt(right);

            if ( countMap.get(rch) > 0 ) { // Found one element from target
                found--;
            }
            countMap.put(rch, countMap.getOrDefault(rch, 0) - 1);
            right++;

            while (found == 0) { // found all chars of substring
                char lch = s.charAt(left);

                // Get the length of window
                if ( minLength > right - left ) {
                    minLength = right - left;
                    minStart = left;
                }

                if ( countMap.get(lch) >= 0 ) {
                    found++;
                }
                countMap.put(lch, countMap.getOrDefault(lch, 0) + 1);
                left++;
            }
        }

        return (minLength == Integer.MAX_VALUE)? "": s.substring(minStart, minStart+minLength);
    }
}
