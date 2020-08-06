package edu.codifyme.leetcode.interview.google.others;

/**
 * EASY:
 * https://leetcode.com/problems/reverse-integer
 *
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 * Input: 123
 * Output: 321
 *
 * Example 2:
 * Input: -123
 * Output: -321
 *
 * Example 3:
 * Input: 120
 * Output: 21
 *
 * Note:
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range:
 * [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer
 * overflows.
 */
public class ReverseInteger {
    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x = x / 10;
        }

        if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
            return 0;
        } else {
            return (int)res;

        }
    }

    // USING Stringbuilder -> Slow
//     public int reverse(int x) {
//         if (x == 0) {
//             return 0;
//         }

//         int negative = (x < 0)? -1: 1;

//         StringBuilder sb = new StringBuilder(String.valueOf(Math.abs(x)));
//         sb = removeLeadingZeroes(sb.reverse());

//         try {
//             int val = Integer.parseInt(sb.toString());
//             return negative * val;
//         } catch (NumberFormatException e) {
//             return 0;
//         }
//     }

//     StringBuilder removeLeadingZeroes(StringBuilder sb) {
//         while (sb.length() > 0 && sb.charAt(0) == '0') {
//             sb.deleteCharAt(0);
//         }
//         return sb;
//     }
}
