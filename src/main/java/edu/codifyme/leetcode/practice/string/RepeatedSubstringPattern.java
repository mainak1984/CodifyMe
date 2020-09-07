package edu.codifyme.leetcode.practice.string;

/**
 * 459. Repeated Substring Pattern
 * EASY: https://leetcode.com/problems/repeated-substring-pattern/
 *
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of
 * the substring together. You may assume the given string consists of lowercase English letters only and its length
 * will not exceed 10000.
 *
 * Example 1:
 * Input: "abab"
 * Output: True
 * Explanation: It's the substring "ab" twice.
 *
 * Example 2:
 * Input: "aba"
 * Output: False
 *
 * Example 3:
 * Input: "abcabcabcabc"
 * Output: True
 * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 *
 * Approach 1:
 * The length of the repeating substring must be a divisor of the length of the input string
 * Search for all possible divisor of str.length, starting for length/2
 * If i is a divisor of length, repeat the substring from 0 to i the number of times i is contained in s.length
 * If the repeated substring is equals to the input str return true
 *
 * Approach 2: Concatenation
 * Repeated pattern string looks like PatternPattern, and the others like Pattern1Pattern2.
 * Let's double the input string:
 * PatternPattern --> PatternPatternPatternPattern
 * Pattern1Pattern2 --> Pattern1Pattern2Pattern1Pattern2
 *
 * Now let's cut the first and the last characters in the doubled string:
 * PatternPattern --> *atternPatternPatternPatter*
 * Pattern1Pattern2 --> *attern1Pattern2Pattern1Pattern*
 *
 * It's quite evident that if the new string contains the input string, the input string is a repeated pattern string.
 */
public class RepeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String str) {
        int l = str.length();
        for(int i=l/2;i>=1;i--) {
            if(l%i==0) {
                int m = l/i;
                String subS = str.substring(0,i);
                StringBuilder sb = new StringBuilder();
                for(int j=0;j<m;j++) {
                    sb.append(subS);
                }
                if(sb.toString().equals(str)) return true;
            }
        }
        return false;
    }

    // Approach 2:
//    public boolean repeatedSubstringPattern(String s) {
//        return (s + s).substring(1, 2 * s.length() - 1).contains(s);
//    }
}
