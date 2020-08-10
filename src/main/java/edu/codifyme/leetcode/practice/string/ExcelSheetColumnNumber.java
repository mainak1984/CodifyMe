package edu.codifyme.leetcode.practice.string;

/**
 * 171. Excel Sheet Column Number
 * EASY: Excel Sheet Column Number
 *
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 *
 * For example:
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 *
 * Example 1:
 * Input: "A"
 * Output: 1
 *
 * Example 2:
 * Input: "AB"
 * Output: 28
 *
 * Example 3:
 * Input: "ZY"
 * Output: 701
 *
 * Constraints:
 * 1 <= s.length <= 7
 * s consists only of uppercase English letters.
 * s is between "A" and "FXSHRXW".
 */
public class ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
        char[] elements = s.toCharArray();

        int val = 0;

        for (int i = 0; i < elements.length; i++) {
            val = val*26 + ((elements[i] - 'A') + 1);
        }

        return val;
    }
}
