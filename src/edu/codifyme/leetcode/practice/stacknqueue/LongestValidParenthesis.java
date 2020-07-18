package edu.codifyme.leetcode.practice.stacknqueue;

import java.util.Stack;

/** HARD: https://leetcode.com/problems/longest-valid-parentheses/submissions/ */

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed)
 * parentheses substring.
 *
 * Example 1:
 *
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * Example 2:
 *
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 *
 * Input: "(())()(()(("
 * Expected: 6
 */

public class LongestValidParenthesis {
    class Pair {
        public Character ch;
        public Integer id;

        public Pair(Character ch, Integer id) {
            this.ch = ch;
            this.id = id;
        }
    }

    public int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();
        Stack<Pair> st1 = new Stack<>();
        int i = 0;
        boolean isValid = true;
        int previousIndex = 0;
        int maxValid = 0;

        for(char ch: chars) {
            if (ch == '(') {
                if ( isValid ) {
                    st1.push(new Pair(ch, previousIndex));
                } else {
                    st1.push(new Pair(ch, i));
                }
                isValid = false;
            } else if (ch == ')') {
                if ( st1.size() != 0 && st1.peek().ch == '(' ) {
                    Pair el = st1.pop();
                    int len = (i - el.id) +1;
                    if ( len > maxValid ) {
                        maxValid = len;
                    }
                    isValid = true;
                    previousIndex = el.id;
                } else {
                    st1.push(new Pair(ch, i));
                    isValid = false;
                }
            }

            i += 1;
        }

        return maxValid;
    }
}
