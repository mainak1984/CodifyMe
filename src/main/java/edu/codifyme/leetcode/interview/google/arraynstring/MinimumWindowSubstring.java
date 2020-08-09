package edu.codifyme.leetcode.interview.google.arraynstring;

import java.util.HashMap;

/**
 * 76. Minimum Window Substring
 * HARD: https://leetcode.com/problems/minimum-window-substring
 *
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in
 * complexity O(n).
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
 * We use the rightright pointer to expand the window until we get a desirable window i.e. a window that contains all of
 * the characters of TT.
 * Once we have a window with all the characters, we can move the left pointer ahead one by one. If the window is still
 * a desirable one we keep on updating the minimum window size.
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

    // TEMPLATE for any substring
//    int findSubstring(string s){
//        vector<int> map(128,0);
//        int counter; // check whether the substring is valid
//        int begin=0, end=0; //two pointers, one point to tail and one  head
//        int d; //the length of substring
//
//        for() { /* initialize the hash map here */ }
//
//        while(end<s.size()){
//
//            if(map[s[end++]]-- ?){  /* modify counter here */ }
//
//            while(/* counter condition */){
//
//                /* update d here if finding minimum*/
//
//                //increase begin to make it invalid/valid again
//
//                if(map[s[begin++]]++ ?){ /*modify counter here*/ }
//            }
//
//            /* update d here if finding maximum*/
//        }
//        return d;
//    }
}
