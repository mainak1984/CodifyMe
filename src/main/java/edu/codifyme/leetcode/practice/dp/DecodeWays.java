package edu.codifyme.leetcode.practice.dp;

import java.util.HashMap;

/**
 * 91. Decode Ways
 * MEDIUM: https://leetcode.com/problems/decode-ways/
 *
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 * The answer is guaranteed to fit in a 32-bit integer.
 *
 * Example 1:
 * Input: s = "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 *
 * Example 2:
 * Input: s = "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 * Example 3:
 * Input: s = "0"
 * Output: 0
 * Explanation: There is no character that is mapped to a number starting with '0'. We cannot ignore a zero when we face
 * it while decoding. So, each '0' should be part of "10" --> 'J' or "20" --> 'T'.
 *
 * Example 4:
 * Input: s = "1"
 * Output: 1
 *
 * Constraints:
 * 1 <= s.length <= 100
 * s contains only digits and may contain leading zero(s).
 *
 * Approach:
 * Approach: DP
 * The iterative approach might be a little bit less intuitive. Let's try to understand it. We use an array for DP to
 * store the results for subproblems. A cell with index i of the dp array is used to store the number of decode ways for
 * substring of s from index 0 to index i-1.
 *
 * We initialize the starting two indices of the dp array. It's similar to relay race where the first runner is given a
 * baton to be passed to the subsequent runners. The first two indices of the dp array hold a baton. As we iterate the
 * dp array from left to right this baton which signifies the number of ways of decoding is passed to the next index or
 * not depending on whether the decode is possible.
 *
 * dp[i] can get the baton from two other previous indices, either i-1 or i-2. Two previous indices are involved since
 * both single and two digit decodes are possible.
 *
 * Unlike the relay race we don't get only one baton in the end. The batons add up as we pass on. If someone has one
 * baton, they can provide a copy of it to everyone who comes to them with a success. Thus, leading to number of ways of
 * reaching the end.
 *
 * dp[i] = Number of ways of decoding substring s[:i]. So we might say dp[i] = dp[i-1] + dp[i-2], which is not always
 * true for this decode ways problem. As shown in the above diagram, only when the decode is possible we add the results
 * of the previous indices. Thus, in this race we don't just pass the baton. The baton is passed to the next index or
 * not depending on possibility of the decode.
 *
 * Approach: Recursion with memoization
 */
public class DecodeWays {
    public int numDecodings(String s) {

        if(s == null || s.length() == 0) {
            return 0;
        }

        // DP array to store the subproblem results
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        // Ways to decode a string of size 1 is 1. Unless the string is '0'.
        // '0' doesn't have a single digit decode.
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for(int i = 2; i < dp.length; i += 1) {

            // Check if successful single digit decode is possible.
            if(s.charAt(i-1) != '0') {
                dp[i] += dp[i-1];
            }

            // Check if successful two digit decode is possible.
            int twoDigit = Integer.valueOf(s.substring(i-2, i));
            if(twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[s.length()];
    }

    // Approach 2: Memoization
    HashMap<Integer, Integer> memo = new HashMap<>();

    private int recursiveWithMemo(int index, String str) {

        // If you reach the end of the string
        // Return 1 for success.
        if (index == str.length()) {
            return 1;
        }

        // If the string starts with a zero, it can't be decoded
        if (str.charAt(index) == '0') {
            return 0;
        }

        if (index == str.length()-1) {
            return 1;
        }

        // Memoization is needed since we might encounter the same sub-string.
        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        int ans = recursiveWithMemo(index+1, str);
        if (Integer.parseInt(str.substring(index, index+2)) <= 26) {
            ans += recursiveWithMemo(index+2, str);
        }

        // Save for memoization
        memo.put(index, ans);

        return ans;
    }
    public int numDecodingsWithMemo(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return recursiveWithMemo(0, s);
    }
}
