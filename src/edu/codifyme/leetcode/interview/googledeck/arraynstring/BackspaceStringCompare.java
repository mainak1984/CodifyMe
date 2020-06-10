package edu.codifyme.leetcode.interview.googledeck.arraynstring;

import java.util.Stack;

/**
 * EASY:
 * https://leetcode.com/explore/interview/card/google/59/array-and-strings/3060/
 * <p>
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace
 * character.
 * <p>
 * Note that after backspacing an empty text, the text will continue empty.
 * <p>
 * Example 1:
 * <p>
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 * Example 2:
 * <p>
 * Input: S = "ab##", T = "c#d#"
 * Output: true
 * Explanation: Both S and T become "".
 * Example 3:
 * <p>
 * Input: S = "a##c", T = "#a#c"
 * Output: true
 * Explanation: Both S and T become "c".
 * Example 4:
 * <p>
 * Input: S = "a#c", T = "b"
 * Output: false
 * Explanation: S becomes "c" while T becomes "b".
 * Note:
 * <p>
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S and T only contain lowercase letters and '#' characters.
 * Follow up:
 * <p>
 * Can you solve it in O(N) time and O(1) space?
 * <p>
 * Approach #2: Two Pointer [Accepted]
 * When writing a character, it may or may not be part of the final string depending on how many backspace keystrokes
 * occur in the future.
 * If instead we iterate through the string in reverse, then we will know how many backspace characters we have seen,
 * and therefore whether the result includes our character.
 */
public class BackspaceStringCompare {
    public boolean backspaceCompare(String S, String T) {
        return build(S).equals(build(T));
    }

    public String build(String S) {
        Stack<Character> ans = new Stack();
        for (char c : S.toCharArray()) {
            if (c != '#')
                ans.push(c);
            else if (!ans.empty())
                ans.pop();
        }
        return String.valueOf(ans);
    }
}
