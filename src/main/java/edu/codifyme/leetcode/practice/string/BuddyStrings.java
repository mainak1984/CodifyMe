package edu.codifyme.leetcode.practice.string;

/**
 * 859. Buddy Strings
 * EASY: https://leetcode.com/problems/buddy-strings/
 *
 * Given two strings A and B of lowercase letters, return true if you can swap two letters in A so the result is equal
 * to B, otherwise, return false.
 *
 * Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at
 * A[i] and A[j]. For example, swapping at indices 0 and 2 in "abcd" results in "cbad".
 *
 * Example 1:
 * Input: A = "ab", B = "ba"
 * Output: true
 * Explanation: You can swap A[0] = 'a' and A[1] = 'b' to get "ba", which is equal to B.
 *
 * Example 2:
 * Input: A = "ab", B = "ab"
 * Output: false
 * Explanation: The only letters you can swap are A[0] = 'a' and A[1] = 'b', which results in "ba" != B.
 *
 * Example 3:
 * Input: A = "aa", B = "aa"
 * Output: true
 * Explanation: You can swap A[0] = 'a' and A[1] = 'a' to get "aa", which is equal to B.
 *
 * Example 4:
 * Input: A = "aaaaaaabc", B = "aaaaaaacb"
 * Output: true
 *
 * Example 5:
 * Input: A = "", B = "aa"
 * Output: false
 *
 * Constraints:
 * 0 <= A.length <= 20000
 * 0 <= B.length <= 20000
 * A and B consist of lowercase letters.
 *
 * Approach:
 * Two cases -
 * - In the case A[i] == A[j] == B[i] == B[j], then the strings A and B are equal. So if A == B, we should check each
 *      index i for two matches with the same value.
 * - In the case A[i] == B[j], A[j] == B[i], (A[i] != A[j]), the rest of the indices match. So if A and B have only
 *      two unmatched indices (say i and j), we should check that the equalities A[i] == B[j] and A[j] == B[i] hold.
 */
public class BuddyStrings {
    public boolean buddyStrings(String A, String B) {
        if (null == A || null == B || A.length() != B.length()) {
            return false;
        }

        char[] arrA = A.toCharArray();
        char[] arrB = B.toCharArray();
        int diff = 0;
        int diffCount = 0;
        int[] freq = new int[26];

        for (int i = 0; i < arrA.length; i++) {
            diff += arrA[i] - arrB[i];
            freq[arrA[i] - 'a'] += 1;

            if (arrA[i] != arrB[i]) {
                diffCount += 1;
            }
        }

        if (diff == 0 && diffCount == 2) {
            return true;
        } else if (diff == 0 && diffCount == 0) {
            for (int j = 0; j < freq.length; j++ ) {
                if (freq[j] > 1) {
                    return true;
                }
            }
        }

        return false;
    }
}
