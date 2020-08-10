package edu.codifyme.leetcode.mocktest.google.recursion;

import java.util.Arrays;

/**
 * 1087. Brace Expansion
 * MEDIUM: https://leetcode.com/problems/brace-expansion/
 *
 * A string S represents a list of words.
 *
 * Each letter in the word has 1 or more options. If there is one option, the letter is represented as is.  If there is
 * more than one option, curly braces delimit the options. For example, "{a,b,c}" represents options ["a", "b", "c"]
 *
 * For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].
 *
 * Return all words that can be formed in this manner, in lexicographical order.
 *
 * Example 1:
 * Input: "{a,b}c{d,e}f"
 * Output: ["acdf","acef","bcdf","bcef"]
 *
 * Example 2:
 * Input: "abcd"
 * Output: ["abcd"]
 *
 * Note:
 * 1 <= S.length <= 50
 * There are no nested curly brackets.
 * All characters inside a pair of consecutive opening and ending curly brackets are different.
 *
 * Approach:
 * Identify the expansion blocks and recur
 */
public class BraceExpansion {
    public String[] expand(String S) {
        S = S.replace("{", "}");
        return recur(S.split("}"), 0, new String[0]);
    }

    public String[] recur(String[] parts, int index, String[] prev) {
        if (index == parts.length) {
            return prev;
        }

        String[] currentParts = parts[index].split(",");

        if(currentParts.length > 1) {
            Arrays.sort(currentParts);
        }

        String[] result = combination(prev, currentParts);

        return recur(parts, index+1, result);
    }

    public String[] combination(String[] comb1, String[] comb2) {
        if (comb1.length == 0) {
            return comb2;
        }

        String[] result = new String[comb1.length * comb2.length];

        for (int i = 0; i < comb1.length; i++) {
            for (int j = 0; j < comb2.length; j++) {
                result[i*comb2.length + j] = comb1[i].concat(comb2[j]);
            }
        }

        return result;
    }
}
