package edu.codifyme.leetcode.interview.google.others;

/**
 * EASY:
 * https://leetcode.com/problems/strobogrammatic-number
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 *
 * Example 1:
 * Input:  "69"
 * Output: true
 *
 * Example 2:
 * Input:  "88"
 * Output: true
 *
 * Example 3:
 * Input:  "962"
 * Output: false
 */
public class StrobogrammaticNumber {
    public boolean isStrobogrammatic(String num) {
        char[] chars = num.toCharArray();

        for (int loop = 0; loop < chars.length; loop++) {
            if (chars[loop] == '1' || chars[loop] == '0' || chars[loop] == '8') {
                if ( chars[loop] != chars[chars.length - 1 - loop] ) {
                    return false;
                } else {
                    continue;
                }
            } else if ( chars[loop] == '9' ) {
                if ( '6' != chars[chars.length - 1 - loop] ) {
                    return false;
                } else {
                    continue;
                }
            } else if ( chars[loop] == '6' ) {
                if ( '9' != chars[chars.length - 1 - loop] ) {
                    return false;
                } else {
                    continue;
                }
            }
            return false;
        }

        return true;
    }
}
