package edu.codifyme.leetcode.interview.googledeck.sortnsearch;

import java.util.HashMap;
import java.util.Map;

/**
 * EASY:
 * https://leetcode.com/problems/valid-anagram
 *
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 *
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 *
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 *
 * Note:
 * You may assume the string contains only lowercase alphabets.
 *
 * Follow up:
 * What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> charMap = new HashMap<>();

        for(char ch: s.toCharArray()) {
            int count = charMap.getOrDefault(ch, 0);
            charMap.put(ch, count + 1);
        }

        for(char ch: t.toCharArray()) {
            int count = charMap.getOrDefault(ch, 0);
            charMap.put(ch, count - 1);
        }

        for(Character ch: charMap.keySet()) {
            if (charMap.get(ch) != 0) {
                return false;
            }
        }
        return true;
    }
}
