package edu.codifyme.leetcode.interview.facebook.arraysnstring;

/**
 * 680. Valid Palindrome II
 * EASY: https://leetcode.com/problems/valid-palindrome-ii/
 *
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 *
 * Example 1:
 * Input: "aba"
 * Output: True
 *
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 *
 * Approach:
 * Intuition
 * If the beginning and end characters of a string are the same (ie. s[0] == s[s.length - 1]), then whether the inner
 * characters are a palindrome (s[1], s[2], ..., s[s.length - 2]) uniquely determines whether the entire string is a
 * palindrome.
 *
 * Algorithm:
 * Suppose we want to know whether s[i], s[i+1], ..., s[j] form a palindrome. If i >= j then we are done. If
 * s[i] == s[j] then we may take i++; j--. Otherwise, the palindrome must be either s[i+1], s[i+2], ..., s[j] or
 * s[i], s[i+1], ..., s[j-1], and we should check both cases.
 */
public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        for(int i=0, j=s.length()-1; i<=j;i++,j--){
            if(s.charAt(i)!=s.charAt(j)){
                return checkPalindrome(s, i+1, j) || checkPalindrome(s, i, j-1);
            }
        }
        return true;
    }

    public boolean checkPalindrome(String s, int start, int end) {
        for(int i=start, j=end; i<=j;i++,j--){
            if(s.charAt(i)!=s.charAt(j)){
                return false;
            }
        }
        return true;
    }
}
