package edu.codifyme.leetcode.practice.mapnset;

import java.util.HashSet;
import java.util.Set;

/**
 * 266. Palindrome Permutation
 * EASY: https://leetcode.com/problems/palindrome-permutation/
 *
 * Given a string, determine if a permutation of the string could form a palindrome.
 *
 * Example 1:
 * Input: "code"
 * Output: false
 *
 * Example 2:
 * Input: "aab"
 * Output: true
 *
 * Example 3:
 * Input: "carerac"
 * Output: true
 *
 * Approach:
 * when we find a character in the string ss that isn't present in the \text{set}set(indicating an odd number of
 * occurrences currently for this character), we put its corresponding entry in the \text{set}set. If we find a
 * character that is already present in the \text{set}set(indicating an even number of occurrences currently for this
 * character), we remove its corresponding entry from the \text{set}set.
 *
 * At the end, the size of \text{set}set indicates the number of elements with odd number of occurrences in ss. If it is
 * lesser than 2, a palindromic permutation of the string ss is possible, otherwise not.
 */
public class PalindromePermutation {
    public boolean canPermutePalindrome(String s) {
        Set< Character > set = new HashSet< >();
        for (int i = 0; i < s.length(); i++) {
            if (!set.add(s.charAt(i)))
                set.remove(s.charAt(i));
        }
        return set.size() <= 1;
    }
}
