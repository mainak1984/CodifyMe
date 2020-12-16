package edu.codifyme.leetcode.practice.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 290. Word Pattern
 * EASY: https://leetcode.com/problems/word-pattern/
 *
 * Given a pattern and a string str, find if str follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 *
 * Example 1:
 * Input: pattern = "abba", str = "dog cat cat dog"
 * Output: true
 *
 * Example 2:
 * Input:pattern = "abba", str = "dog cat cat fish"
 * Output: false
 *
 * Example 3:
 * Input: pattern = "aaaa", str = "dog cat cat dog"
 * Output: false
 *
 * Example 4:
 * Input: pattern = "abba", str = "dog dog dog dog"
 * Output: false
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains lowercase letters that may be separated by a
 * single space.
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> bijection = new HashMap<>();
        String[] parts = str.split(" ");
        int i = 0;

        if (pattern.length() != parts.length) {
            return false;
        }

        for (char ch: pattern.toCharArray()) {
            String val = bijection.get(ch);
            if (val == null) {
                bijection.put(ch, parts[i]);
            } else {
                if (!val.equals(parts[i])) {
                    return false;
                }
            }
            i++;
        }

        return new HashSet(bijection.values()).size() == bijection.size();
    }
}
