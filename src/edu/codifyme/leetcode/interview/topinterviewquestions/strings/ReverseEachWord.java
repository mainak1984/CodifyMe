package edu.codifyme.leetcode.interview.topinterviewquestions.strings;

/**
 * 557. Reverse Words in a String III
 * EASY: https://leetcode.com/problems/reverse-words-in-a-string-iii/
 *
 * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 *
 * Example 1:
 * Input: "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 * Note: In the string, each word is separated by single space and there will not be any extra space in the string.
 */
public class ReverseEachWord {
    public String reverseWords(String s) {
        if (s == null ) {
            return null;
        }

        String[] parts = s.split(" ");
        return split(parts, 0, parts.length - 1);
    }

    private String split(String[] parts, int start, int end) {
        if (start == end) {
            StringBuilder str = new StringBuilder();
            str.append(parts[start]);
            str = str.reverse();
            return str.toString();
        } else if (start > end) {
            return "";
        }

        int mid = (start + end) / 2;
        String left = split(parts, start, mid);
        String right = split(parts, mid + 1, end);
        return left + " " + right;
    }
}
