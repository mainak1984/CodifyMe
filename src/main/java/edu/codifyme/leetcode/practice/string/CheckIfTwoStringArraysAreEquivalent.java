package edu.codifyme.leetcode.practice.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 1662. Check If Two String Arrays are Equivalent
 * EASY: https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/
 *
 * Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.
 * A string is represented by an array if the array elements concatenated in order forms the string.
 *
 * Example 1:
 * Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
 * Output: true
 * Explanation:
 * word1 represents string "ab" + "c" -> "abc"
 * word2 represents string "a" + "bc" -> "abc"
 * The strings are the same, so return true.
 *
 * Example 2:
 * Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
 * Output: false
 *
 * Example 3:
 * Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
 * Output: true
 *
 * Constraints:
 * 1 <= word1.length, word2.length <= 103
 * 1 <= word1[i].length, word2[i].length <= 103
 * 1 <= sum(word1[i].length), sum(word2[i].length) <= 103
 * word1[i] and word2[i] consist of lowercase letters.
 *
 * Approach:
 * We can iterate over each character in one string array and compare the corresponding character in the other string array.
 * To achieve this, we need some index to track the character in the other string array.
 * Here we use two indexes: stringIndex and characterIndex. stringIndex points to the index of the string in the string
 * array, and characterIndex represents the index of the character in the string.
 *
 * Algorithm
 * Step 1: Iterate over word1 and check if the corresponding character in word2 is the same.
 * Note: You can switch the position of word1 and word2.
 * Challenge: Can you implement the code yourself without seeing our implementations?
 */
public class CheckIfTwoStringArraysAreEquivalent {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int stringIndex = 0;
        int characterIndex = 0;
        int word2Length = word2.length;
        List<Integer> word2LengthList = new ArrayList<>();
        for (String s : word2) {
            word2LengthList.add(s.length());
        }
        for (String s : word1) {
            for (char c : s.toCharArray()) {
                if (stringIndex >= word2Length ||
                        c != word2[stringIndex].charAt(characterIndex)) {
                    return false;
                }
                characterIndex++;
                if (characterIndex == word2LengthList.get(stringIndex)) {
                    stringIndex++;
                    characterIndex = 0;
                }
            }
        }
        return true;
    }
}
