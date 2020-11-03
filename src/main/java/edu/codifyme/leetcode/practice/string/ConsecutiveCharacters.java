package edu.codifyme.leetcode.practice.string;

/**
 * 1446. Consecutive Characters
 * EASY: https://leetcode.com/problems/consecutive-characters/
 *
 * Given a string s, the power of the string is the maximum length of a non-empty substring that contains only one
 * unique character.
 *
 * Return the power of the string.
 *
 * Example 1:
 * Input: s = "leetcode"
 * Output: 2
 * Explanation: The substring "ee" is of length 2 with the character 'e' only.
 *
 * Example 2:
 * Input: s = "abbcccddddeeeeedcba"
 * Output: 5
 * Explanation: The substring "eeeee" is of length 5 with the character 'e' only.
 *
 * Example 3:
 * Input: s = "triplepillooooow"
 * Output: 5
 *
 * Example 4:
 * Input: s = "hooraaaaaaaaaaay"
 * Output: 11
 *
 * Example 5:
 * Input: s = "tourist"
 * Output: 1
 *
 * Constraints:
 * 1 <= s.length <= 500
 * s contains only lowercase English letters.
 *
 * Approach:
 * We can iterate over the given string, and use a variable count to record the length of that substring.
 * When the next character is the same as the previous one, we increase count by one. Else, we reset count to 1.
 * With this method, when reaching the end of a substring with the same characters, count will be the length of that
 * substring, since we reset the count when that substring starts, and increase count when iterate that substring.
 * Therefore, the maximum value of count is what we need. Another variable is needed to store the maximum while iterating.
 */
public class ConsecutiveCharacters {
    public int maxPower(String s) {
        char prev = 0;
        int max = 1;
        int count = 0;

        for (char ch: s.toCharArray()) {
            if (prev == ch) {
                count++;
                max = max < count? count: max;
            } else {
                prev = ch;
                count = 1;
            }
        }

        return max;
    }
}
