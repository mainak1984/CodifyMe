package edu.codifyme.leetcode.interview.googledeck.arraynstring;

/** Easy: https://leetcode.com/problems/valid-parentheses/ **/

import java.util.Stack;

/**
 * 20. Valid Parentheses
 * Easy
 *
 * 3732
 *
 * 183
 *
 * Favorite
 *
 * Share
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 *
 * Input: "()"
 * Output: true
 * Example 2:
 *
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 *
 * Input: "(]"
 * Output: false
 * Example 4:
 *
 * Input: "([)]"
 * Output: false
 * Example 5:
 *
 * Input: "{[]}"
 * Output: true
 */
public class ValidParenthesis {
    private boolean isOpen(char ch) {
        if (ch == '(' || ch == '{' || ch == '[')
            return true;
        else
            return false;
    }
    private boolean isClose(char ch) {
        if (ch == ')' || ch == '}' || ch == ']')
            return true;
        else
            return false;
    }
    private boolean isMatching(char ch1, char ch2) {
        if (ch1 == '(' && ch2 == ')')
            return true;
        else if (ch1 == '{' && ch2 == '}')
            return true;
        if (ch1 == '[' && ch2 == ']')
            return true;
        else
            return false;
    }

    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        char[] chArr = s.toCharArray();

        for (char ch: chArr) {
            if (isOpen(ch)) {
                st.push(ch);
            } else if (st.size() != 0 && isMatching(st.peek(), ch)) {
                st.pop();
            } else {
                return false;
            }
        }

        if (st.size() != 0){
            return false;
        }

        return true;
    }
}
