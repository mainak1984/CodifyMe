package edu.codifyme.leetcode.interview.googledeck.mockprep;

import java.util.Arrays;

/**
 * MEDIUM:
 * https://leetcode.com/problems/find-and-replace-in-string/
 *
 * To some string S, we will perform some replacement operations that replace groups of letters with new ones (not
 * necessarily the same size).
 * Each replacement operation has 3 parameters: a starting index i, a source word x and a target word y.  The rule is
 * that if x starts at position i in the original string S, then we will replace that occurrence of x with y.  If not,
 * we do nothing.
 * For example, if we have S = "abcd" and we have some replacement operation i = 2, x = "cd", y = "ffff", then because
 * "cd" starts at position 2 in the original string S, we will replace it with "ffff".
 * Using another example on S = "abcd", if we have both the replacement operation i = 0, x = "ab", y = "eee", as well
 * as another replacement operation i = 2, x = "ec", y = "ffff", this second operation does nothing because in the
 * original string S[2] = 'c', which doesn't match x[0] = 'e'.
 * All these operations occur simultaneously.  It's guaranteed that there won't be any overlap in replacement: for
 * example, S = "abc", indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.
 *
 * Example 1:
 * Input: S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
 * Output: "eeebffff"
 * Explanation: "a" starts at index 0 in S, so it's replaced by "eee".
 * "cd" starts at index 2 in S, so it's replaced by "ffff".
 *
 * Example 2:
 * Input: S = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
 * Output: "eeecd"
 * Explanation: "ab" starts at index 0 in S, so it's replaced by "eee".
 * "ec" doesn't starts at index 2 in the original S, so we do nothing.
 *
 * Notes:
 * 0 <= indexes.length = sources.length = targets.length <= 100
 * 0 < indexes[i] < S.length <= 1000
 * All characters in given inputs are lowercase letters.
 *
 * Approach: Sort the modifications in reverse order and do the edit.
 *
 * Better Approach:
 * In Java, the idea is to build an array match that tells us match[ix] = j whenever S[ix] is the head of a successful
 * replacement operation j: that is, whenever S[ix:].startswith(sources[j]).
 *
 * After, we build the answer using this match array. For each index ix in S, we can use match to check whether S[ix] is
 * being replaced or not. We repeatedly either write the next character S[ix], or groups of characters
 * targets[match[ix]], depending on the value of match[ix].
 */
public class FindAndReplaceInString {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        int N = S.length();
        int[] match = new int[N];
        Arrays.fill(match, -1);

        for (int i = 0; i < indexes.length; ++i) {
            int ix = indexes[i];
            if (S.substring(ix, ix + sources[i].length()).equals(sources[i]))
                match[ix] = i;
        }

        StringBuilder ans = new StringBuilder();
        int ix = 0;
        while (ix < N) {
            if (match[ix] >= 0) {
                ans.append(targets[match[ix]]);
                ix += sources[match[ix]].length();
            } else {
                ans.append(S.charAt(ix++));
            }
        }
        return ans.toString();
    }

//    class Input {
//        int index;
//        String source;
//        String target;
//
//        public Input(int index, String source, String target) {
//            this.index = index;
//            this.source = source;
//            this.target = target;
//        }
//    }
//
//    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
//        StringBuilder sb = new StringBuilder(S);
//        Input[] inputs = new Input[indexes.length];
//
//        for (int loop = 0; loop < indexes.length; loop++) {
//            inputs[loop] = new Input(indexes[loop], sources[loop], targets[loop]);
//        }
//
//        Arrays.sort(inputs, (a,b) -> {return a.index - b.index;});
//
//        for (int loop = inputs.length-1; loop >= 0; loop--) {
//            if ( inputs[loop].index + inputs[loop].source.length() <= S.length() &&
//                    S.substring(inputs[loop].index, inputs[loop].index+inputs[loop].source.length()).equals(inputs[loop].source)) {
//                // possible replace
//                sb = sb.delete(inputs[loop].index, inputs[loop].index+inputs[loop].source.length());
//                sb = sb.insert(inputs[loop].index, inputs[loop].target);
//            }
//        }
//
//        return sb.toString();
//    }
}
