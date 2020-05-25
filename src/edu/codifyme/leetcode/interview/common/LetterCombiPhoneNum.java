package edu.codifyme.leetcode.interview.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. Letter Combinations of a Phone Number
 * EASY: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 * Example:
 *
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class LetterCombiPhoneNum {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        char[] chars = digits.toCharArray();
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        if ( digits.length() == 0 ) {
            return result;
        }

        getCombi(chars, 0, "", map, result);

        return result;
    }

    private void getCombi(char[] digits, int currPos, String currOutput, Map<Character, String> map, List<String> result) {
        if ( currPos >= digits.length ) {
            result.add(currOutput);
            return;
        }

        String words = map.get(digits[currPos]);

        if ( words != null ) {
            for (char ch: words.toCharArray()) {
                getCombi(digits, currPos + 1, currOutput + String.valueOf(ch), map, result);
            }
        }
    }
}
