package edu.codifyme.leetcode.interview.google.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. Generate Parentheses
 * MEDIUM:
 * https://leetcode.com/problems/generate-parentheses
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        if ( n < 0 ) {
            return result;
        } else {
            generate(n, n, result, "");
            return result;
        }
    }

    private void generate(int left, int right, List<String> result, String concat) {
        if ( left == 0 && right == 0 ) {
            result.add(concat);
            return;
        }

        if ( left > 0 ) {
            generate(left - 1, right, result, concat.concat("("));
        }
        if ( right > left ) {
            generate(left, right - 1, result, concat.concat(")"));
        }
    }
}
