package edu.codifyme.leetcode.practice.string;

/**
 * 409. Longest Palindrome
 * EASY: https://leetcode.com/problems/longest-palindrome/
 *
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can
 * be built with those letters.
 *
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 *
 * Note:
 * Assume the length of given string will not exceed 1,010.
 *
 * Example:
 * Input:
 * "abccccdd"
 *
 * Output:
 * 7
 *
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 *
 * Approach:
 * Count the even chars and odd chars
 */
public class LongestPalindrome {
//     public int longestPalindrome(String s) {
//         Map<Character, Integer> map = new HashMap<>();

//         for (char ch: s.toCharArray()) {
//             map.put(ch, map.getOrDefault(ch, 0) + 1);
//         }

//         int single = 0;
//         int dbl = 0;

//         for (int count: map.values()) {
//             int div = count / 2;
//             int rem = count % 2;
//             dbl += div;
//             single+= rem;
//         }

//         dbl *= 2;

//         return (single > 0)? dbl + 1: dbl;
//     }

    public int longestPalindrome(String s) {
        int[] chars = new int[128];
        char[] t = s.toCharArray();
        for(char c : t){
            chars[c]++;HIn
        }
        int single = 0;
        for(int n : chars){
            if(n % 2 != 0){
                single++;
            }
        }
        return single > 1 ? t.length - single + 1 : t.length;
    }
}
