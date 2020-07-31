package edu.codifyme.leetcode.interview.mocktest.google.stacknqueue;

/**
 * EASY: Remove All Adjacent Duplicates In String
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
 *
 * Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and
 * removing them. We repeatedly make duplicate removals on S until we no longer can.
 * Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.
 *
 * Example 1:
 * Input: "abbaca"
 * Output: "ca"
 * Explanation:
 * For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible
 * move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 *
 * Note:
 * 1 <= S.length <= 20000
 * S consists only of English lowercase letters.
 *
 * Approach 1:
 * Use 2 pointers and when detected as match, remove the items
 *
 * Approach 2:
 * Use a stack. Check if top of stack matches current char, remove it. Else push
 */
public class RemoveAllAdjacentDuplicates {
    public String removeDuplicates(String S) {
        StringBuilder sb = new StringBuilder();
        int sbLength = 0;
        for(char character : S.toCharArray()) {
            if (sbLength != 0 && character == sb.charAt(sbLength - 1))
                sb.deleteCharAt(sbLength-- - 1);
            else {
                sb.append(character);
                sbLength++;
            }
        }
        return sb.toString();
    }

    // Efficient implementation
//    public String removeDuplicates(String S) {
//        char[] chars = S.toCharArray();
//        int end = -1;
//        for (char c : chars) {
//            if (end >= 0 && chars[end] == c) {
//                --end;
//            } else {
//                chars[++end] = c;
//            }
//        }
//        return String.valueOf(chars, 0, end + 1);
//    }

    // Approach 1:
//    public String removeDuplicates(String S) {
//        if (null == S || S.length() < 2) {
//            return S;
//        }
//
//        char[] result = S.toCharArray();
//        int left = 0;
//        int right = 1;
//
//        while (right < S.length()) {
//            // detect equal character
//            if (S.charAt(left) == S.charAt(right)) {
//                result[left--] = 0;
//                result[right++] = 0;
//            } else {
//                left = right;
//                right++;
//            }
//
//            while (left >= 0 && result[left] == 0) {
//                left--;
//            }
//
//            if (left < 0) {
//                left = right;
//                right++;
//            }
//        }
//
//        StringBuilder sb = new StringBuilder();
//        for (char ch: result) {
//            if (ch != 0) {
//                sb.append(ch);
//            }
//        }
//
//        return sb.toString();
//    }
}
